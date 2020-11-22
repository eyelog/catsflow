package ru.eyelog.catsflow.router

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.eyelog.catsflow.R
import ru.eyelog.core_common.BackListener

abstract class FragmentFlowNavigation : Fragment(), BackListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_flow_navigation, container, false)

    fun addScreen(fragment: Fragment, screenName: String) {
        if (isVisible) {
            childFragmentManager.beginTransaction()
                .replace(R.id.flSceneContainer, fragment, screenName)
                .addToBackStack(screenName)
                .commit()
        }
    }

    fun noReplaceScreen(fragment: Fragment, screenName: String) {
        if (isVisible) {
            childFragmentManager.beginTransaction()
                .add(R.id.flSceneContainer, fragment, screenName)
                .addToBackStack(screenName)
                .commit()
        }
    }

    /**
     * Метод возвращает текущий фрагмент выводимый в сцене навигации.
     *
     * @return текущий фрагмент сцены, если он есть.
     */
    fun childFragment(): Fragment? = (childFragmentManager.findFragmentById(R.id.flSceneContainer) as Fragment?)

    fun goBack() = childFragmentManager.popBackStack()

    fun resetFlow() = childFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.flSceneContainer) == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.flSceneContainer, createInitFragment())
                .commit()
        }
    }

    override fun onBackClick(): Boolean {
        val child = childFragmentManager.findFragmentById(R.id.flSceneContainer)
        return child != null && child is BackListener && child.onBackClick()
    }

    /**
     * Метод создания начального экрана в последовательности экранов.
     */
    abstract fun createInitFragment(): Fragment
}