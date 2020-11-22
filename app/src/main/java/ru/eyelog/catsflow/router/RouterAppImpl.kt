package ru.eyelog.catsflow.router

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.eyelog.feature_favorites.ui.FavoriteCatsFragment
import ru.eyelog.feature_mainlist.ui.MainListFragment
import ru.eyelog.catsflow.utils.extensions.newInstance

class RouterAppImpl(private val containerId: Int) : BaseRouterApp() {

    private val manager: FragmentManager? get() = activity?.supportFragmentManager

    override fun routeToMainList() =
        addFlowScreen(newInstance<FavoriteCatsFragment>(), MAIN_LIST_SCREEN_TAG)

    override fun routeToFavoriteList() =
        addFlowScreen(newInstance<MainListFragment>(), FAVORITE_LIST_SCREEN_TAG)

    override fun routeBack() = goBack()

    private fun addFlowScreen(fragment: Fragment, screenName: String) {
        (manager?.findFragmentById(containerId) as FragmentFlowNavigation?)?.addScreen(
            fragment,
            screenName
        )
    }

    private fun goBack() {
        (manager?.findFragmentById(containerId) as FragmentFlowNavigation?)?.goBack()
    }

    companion object {
        private const val MAIN_LIST_SCREEN_TAG = "main_list_screen"
        private const val FAVORITE_LIST_SCREEN_TAG = "favorite_list_screen"
    }
}