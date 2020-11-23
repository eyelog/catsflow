package ru.eyelog.catsflow.router

import ru.eyelog.catsflow.activity.mainscreen.MainActivity

abstract class BaseRouterApp : RouterApp {

    protected var activity: MainActivity? = null

    override fun onAttach(activity: MainActivity) {
        this.activity = activity
    }

    override fun onDetach() {
        this.activity = null
    }
}