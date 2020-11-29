package ru.eyelog.core_common.extensions

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

fun <T> ImageView.loadImage(
        model: T,
        placeholderRes: Int? = null,
        loadingListener: ((isSuccess: Boolean) -> Unit)? = null,
        isPriorityHigh: Boolean = false
) {
    val builder = Glide.with(context)
            .asBitmap()
            .load(model)
            .priority(
                    if (isPriorityHigh) {
                        Priority.HIGH
                    } else {
                        Priority.NORMAL
                    }
            )
            .listener(object : RequestListener<Bitmap> {
                override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                ): Boolean {
                    loadingListener?.invoke(true)
                    return false
                }

                override fun onLoadFailed(
                        p0: GlideException?,
                        p1: Any?,
                        target: com.bumptech.glide.request.target.Target<Bitmap>?,
                        p3: Boolean
                ): Boolean {
                    loadingListener?.invoke(false)
                    return false
                }
            })
            .centerCrop()
    placeholderRes?.let {
        builder.placeholder(it)
    }
    builder.into(this)
}