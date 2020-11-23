package ru.eyelog.catsflow.activity.mainscreen

import android.os.Bundle
import ru.eyelog.catsflow.R
import ru.eyelog.catsflow.activity.di.DaggerComponentMain
import ru.eyelog.catsflow.router.RouterApp
import ru.eyelog.core_common.abstractions.BaseActivity
import ru.eyelog.core_common.extensions.findComponentDependencies
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var router: RouterApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerComponentMain.builder()
            .withActivity(this)
            .withDependencies(findComponentDependencies())
            .build()
            .inject(this)

        router.onAttach(this)
        lifecycle.addObserver(mainViewModel)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tap_main_list -> {
                    router.routeToMainList()
                    true
                }
                R.id.tap_fav_list -> {
                    router.routeToFavoriteList()
                    true
                }
                else -> false
            }
        }
    }
}
