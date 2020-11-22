package ru.eyelog.catsflow.di

import dagger.MapKey
import ru.eyelog.core_common.ComponentDependencies
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val value: KClass<out ComponentDependencies>)