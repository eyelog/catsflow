package ru.eyelog.core_data.network.interceptors

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.internal.http.promisesBody
import okio.Buffer
import okio.GzipSource
import ru.eyelog.core_data.utils.ApiException
import timber.log.Timber
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import java.util.concurrent.TimeUnit

class InterceptorLogging @JvmOverloads constructor(
    private val logger: Logger = Logger.DEFAULT
) : Interceptor {

    @Volatile
    private var headersToRedact = emptySet<String>()

    @set:JvmName("level")
    @Volatile
    var level = Level.NONE

    enum class Level {
        /** No logs. */
        NONE,

        /**
         * Logs request and response lines.
         *
         * Example:
         * ```
         * --> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
         * ```
         */
        BASIC,

        /**
         * Logs request and response lines and their respective headers.
         *
         * Example:
         * ```
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * ```
         */
        HEADERS,

        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         *
         * Example:
         * ```
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * ```
         */
        BODY
    }

    interface Logger {

        fun logError(error: ApiException)

        fun logError(message: String)

        fun logDebug(message: String)

        companion object {

            /** A [Logger] defaults output appropriate. */
            @JvmField
            val DEFAULT: Logger = object : Logger {

                override fun logError(error: ApiException) = Timber.e(error)

                override fun logError(message: String) = Timber.e(message)

                override fun logDebug(message: String) = Timber.d(message)
            }
        }
    }

    class LoggerMethod(private val responseCode: Int) {

        fun log(message: String) {
            when (responseCode) {
                RESPONSE_SUCCESS_CODE -> Timber.d(message)
                else -> Timber.e(message)
            }
        }
    }

    @Deprecated(
        message = "Moved to var. Replace setLevel(...) with level(...) to fix Java",
        replaceWith = ReplaceWith(expression = "apply { this.level = level }"),
        level = DeprecationLevel.WARNING
    )
    fun setLevel(level: Level) = apply {
        this.level = level
    }

    @JvmName("-deprecated_level")
    @Deprecated(
        message = "moved to var",
        replaceWith = ReplaceWith(expression = "level"),
        level = DeprecationLevel.ERROR
    )
    fun getLevel(): Level = level

    @Suppress("Detekt.All")
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val level = this.level

        val request = chain.request()
        if (level == Level.NONE) {
            return chain.proceed(request)
        }

        val logBody = level == Level.BODY
        val logHeaders = logBody || level == Level.HEADERS

        val requestBody = request.body

        val requestStartMessage = ("--> ${request.method} ${request.url}")

        val startNs = System.nanoTime()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            logger.logError(requestStartMessage)
            logger.logError("<-- HTTP FAILED: $e")
            throw e
        }
        val loggerMethod = LoggerMethod(response.code)
        loggerMethod.log(requestStartMessage)

        if (logHeaders) {
            if (requestBody != null) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                requestBody.contentType()?.let {
                    loggerMethod.log("Content-Type: $it")
                }
                if (requestBody.contentLength() != -1L) {
                    loggerMethod.log("Content-Length: ${requestBody.contentLength()}")
                }
            }

            val headers = request.headers
            for (i in 0 until headers.size) {
                val name = headers.name(i)
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equals(name, ignoreCase = true) &&
                    !"Content-Length".equals(name, ignoreCase = true)
                ) {
                    logHeader(loggerMethod, headers, i)
                }
            }

            if (!logBody || requestBody == null) {
                loggerMethod.log("--> END ${request.method}")
            } else if (bodyHasUnknownEncoding(request.headers)) {
                loggerMethod.log("--> END ${request.method} (encoded body omitted)")
            } else if (requestBody.isDuplex()) {
                loggerMethod.log("--> END ${request.method} (duplex request body omitted)")
            } else {
                val buffer = Buffer()
                requestBody.writeTo(buffer)

                val contentType = requestBody.contentType()
                val charset: Charset = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    contentType?.charset(UTF_8) ?: UTF_8
                } else {
                    TODO("VERSION.SDK_INT < KITKAT")
                }

                loggerMethod.log("")
                val multipartType = "multipart/form-core_data".toMediaTypeOrNull()
                if (buffer.isProbablyUtf8()
                    && contentType?.type != multipartType?.type
                    && contentType?.subtype != multipartType?.subtype
                ) {
                    loggerMethod.log(buffer.readString(charset))
                    loggerMethod.log("--> END ${request.method} (${requestBody.contentLength()}-byte body)")
                } else {
                    loggerMethod.log(
                        "--> END ${request.method} (binary ${requestBody.contentLength()}-byte body omitted)"
                    )
                }
            }
        }

        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        val responseBody = response.body!!
        val contentLength = responseBody.contentLength()
        val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"
        loggerMethod.log(
            "<-- ${response.code}${if (response.message.isEmpty()) "" else ' ' + response.message} " +
                    "${response.request.url} (${tookMs}ms${if (!logHeaders) ", $bodySize body" else ""})"
        )

        if (logHeaders) {
            val headers = response.headers
            for (i in 0 until headers.size) {
                logHeader(loggerMethod, headers, i)
            }

            if (!logBody || !response.promisesBody()) {
                loggerMethod.log("<-- END HTTP")
            } else if (bodyHasUnknownEncoding(response.headers)) {
                loggerMethod.log("<-- END HTTP (encoded body omitted)")
            } else {
                val source = responseBody.source()
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                var buffer = source.buffer

                var gzippedLength: Long? = null
                if ("gzip".equals(headers["Content-Encoding"], ignoreCase = true)) {
                    gzippedLength = buffer.size
                    GzipSource(buffer.clone()).use { gzippedResponseBody ->
                        buffer = Buffer()
                        buffer.writeAll(gzippedResponseBody)
                    }
                }

                val contentType = responseBody.contentType()
                val charset: Charset = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    contentType?.charset(UTF_8) ?: UTF_8
                } else {
                    TODO("VERSION.SDK_INT < KITKAT")
                }

                if (!buffer.isProbablyUtf8()) {
                    loggerMethod.log("")
                    loggerMethod.log("<-- END HTTP (binary ${buffer.size}-byte body omitted)")
                    return response
                }

                if (contentLength != 0L) {
                    loggerMethod.log("")
                    loggerMethod.log(buffer.clone().readString(charset))
                }

                if (gzippedLength != null) {
                    loggerMethod.log("<-- END HTTP (${buffer.size}-byte, $gzippedLength-gzipped-byte body)")
                } else {
                    loggerMethod.log("<-- END HTTP (${buffer.size}-byte body)")
                }
            }
        }

        if (response.code != 200) {
            logger.logError(ApiException("The server returned no 200 code"))
        }
        return response
    }

    private fun logHeader(loggerMethod: LoggerMethod, headers: Headers, i: Int) {
        val value = if (headers.name(i) in headersToRedact) "██" else headers.value(i)
        loggerMethod.log(headers.name(i) + ": " + value)
    }

    private fun bodyHasUnknownEncoding(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"] ?: return false
        return !contentEncoding.equals("identity", ignoreCase = true) &&
                !contentEncoding.equals("gzip", ignoreCase = true)
    }

    @Suppress("Detekt.All")
    private fun Buffer.isProbablyUtf8(): Boolean {
        try {
            val prefix = Buffer()
            val byteCount = size.coerceAtMost(64)
            copyTo(prefix, 0, byteCount)
            for (i in 0 until 16) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            return true
        } catch (_: EOFException) {
            return false // Truncated UTF-8 sequence.
        }
    }

    companion object {
        private const val RESPONSE_SUCCESS_CODE = 200
    }
}