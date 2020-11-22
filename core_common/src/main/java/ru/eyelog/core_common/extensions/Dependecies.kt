package ru.eyelog.core_common.extensions

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import ru.eyelog.core_common.ComponentDependencies
import ru.eyelog.core_common.ComponentDependenciesProvider
import ru.eyelog.core_common.HasComponentDependencies

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> Activity.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> Context.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

fun Fragment.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = when {
        activity?.application is HasComponentDependencies -> activity?.application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable (Fragment) dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

fun Activity.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = when (this.application) {
        is HasComponentDependencies -> this.application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable (Activity) dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

fun Context.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = when (this) {
        is HasComponentDependencies -> this as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable (Context) dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

