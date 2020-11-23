package ru.eyelog.catsflow.router

import ru.eyelog.catsflow.RouterCats
import ru.eyelog.catsflow.activity.mainscreen.MainActivity
import ru.eyelog.feature_favorites.RouterFavoritesList
import ru.eyelog.feature_mainlist.RouterMainList

interface RouterApp: RouterCats, RouterMainList, RouterFavoritesList {

    fun onAttach(activity: MainActivity)

    fun onDetach()
}