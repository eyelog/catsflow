package ru.eyelog.core_data.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import ru.eyelog.core_common.extensions.findComponentDependencies
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Named

@Excludes(OkHttpLibraryGlideModule::class)
@GlideModule
class CustomGlideModule : AppGlideModule() {

    @field:[Inject Named("glideOkHttpClient")]
    lateinit var client: OkHttpClient

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        Timber.d("Registering Glide components...")
        DaggerComponentGlide.builder()
            .withDependencies(context.findComponentDependencies())
            .build()
            .inject(this)

        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
    }
}