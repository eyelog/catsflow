package ru.eyelog.catsflow.activity.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import ru.eyelog.catsflow.R
import ru.eyelog.catsflow.activity.di.DaggerComponentSplash
import ru.eyelog.catsflow.activity.mainscreen.MainActivity
import ru.eyelog.core_common.abstractions.BaseActivity
import javax.inject.Inject

class SplashActivity: BaseActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DaggerComponentSplash.builder()
            .withActivity(this)
            .build()
            .inject(this)

        val imageView: ImageView = findViewById<View>(R.id.ivLoadingBar) as ImageView
        val imageViewTarget = DrawableImageViewTarget(imageView)
        Glide.with(this).load(R.drawable.long_cat_mode).into(imageViewTarget)

        viewModel.splashLiveData.observeForever {

            Intent(this, MainActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                .let(::startActivity)
        }
    }
}