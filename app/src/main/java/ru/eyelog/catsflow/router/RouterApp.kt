package ru.eyelog.catsflow.router

import ru.eyelog.catsflow.activity.mainscreen.MainActivity
import ru.eyelog.feature_favorites.RouterFavoritesList
import ru.eyelog.feature_mainlist.RouterMainList

interface RouterApp: RouterMainList, RouterFavoritesList {

    fun onAttach(activity: MainActivity)

    fun onDetach()
}