package ru.eyelog.catsflow.router

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.eyelog.feature_mainlist.ui.MainListFragment
import ru.eyelog.core_common.extensions.newInstance
import ru.eyelog.feature_favorites.ui.FavoriteCatsFragment

class RouterAppImpl(private val containerId: Int) : BaseRouterApp() {

    private val manager: FragmentManager? get() = activity?.supportFragmentManager

    private val mainListFragment: FragmentFlowNavigation by lazy {
        manager?.findFragmentByTag(MAIN_LIST_SCREEN_TAG) as? FragmentFlowNavigation
            ?: FragmentMainListTab()
    }

    class FragmentMainListTab : FragmentFlowNavigation() {
        override fun createInitFragment(): Fragment = newInstance<MainListFragment>()
    }

    override fun routeToMainList() =
        replaceTabFlowScreen(mainListFragment, MAIN_LIST_SCREEN_TAG)

    private val favoriteCatsFragment: FragmentFlowNavigation by lazy {
        manager?.findFragmentByTag(FAVORITE_LIST_SCREEN_TAG) as? FragmentFlowNavigation
            ?: FragmentFavoriteCatsTab()
    }

    class FragmentFavoriteCatsTab : FragmentFlowNavigation() {
        override fun createInitFragment(): Fragment = newInstance<FavoriteCatsFragment>()
    }

    override fun routeToFavoriteList() =
        replaceTabFlowScreen(favoriteCatsFragment, MAIN_LIST_SCREEN_TAG)


    override fun routeBack() = goBack()

    private fun addFlowScreen(fragment: Fragment, screenName: String) {
        (manager?.findFragmentById(containerId) as FragmentFlowNavigation?)?.addScreen(
            fragment,
            screenName
        )
    }

    private fun replaceTabFlowScreen(
        fragment: FragmentFlowNavigation,
        screenName: String
    ) {
        manager?.let {
            it.beginTransaction()
                .replace(containerId, fragment, screenName)
                .addToBackStack(null)
                .commit()
        }
    }


    private fun goBack() {
        (manager?.findFragmentById(containerId) as FragmentFlowNavigation?)?.goBack()
    }

    companion object {
        private const val MAIN_LIST_SCREEN_TAG = "main_list_screen"
        private const val FAVORITE_LIST_SCREEN_TAG = "favorite_list_screen"
    }
}