package ru.eyelog.core_data.utils

interface ComponentDependencies

typealias ComponentDependenciesProvider =
        Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}