package ru.eyelog.core_common

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

inline fun <reified T : ru.eyelog.core_common.ComponentDependencies> Fragment.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ru.eyelog.core_common.ComponentDependencies> Activity.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ru.eyelog.core_common.ComponentDependencies> Context.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

fun Fragment.findComponentDependenciesProvider(): ru.eyelog.core_common.ComponentDependenciesProvider {
    val hasDaggerProviders = when {
        activity?.application is ru.eyelog.core_common.HasComponentDependencies -> activity?.application as ru.eyelog.core_common.HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

fun Activity.findComponentDependenciesProvider(): ru.eyelog.core_common.ComponentDependenciesProvider {
    val hasDaggerProviders = when {
        this.application is ru.eyelog.core_common.HasComponentDependencies -> this.application as ru.eyelog.core_common.HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

fun Context.findComponentDependenciesProvider(): ru.eyelog.core_common.ComponentDependenciesProvider {
    val hasDaggerProviders = when {
        this is ru.eyelog.core_common.HasComponentDependencies -> this as ru.eyelog.core_common.HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

