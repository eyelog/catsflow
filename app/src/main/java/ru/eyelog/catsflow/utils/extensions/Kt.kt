package ru.eyelog.catsflow.utils.extensions

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

/**
 * Функция создания фрагмента с заданными аргументами.
 *
 * @param params - параметры, необходимые для передаче фрагменту в качестве аргументов.
 */
inline fun <reified T : Fragment> newInstance(vararg params: Pair<String, Any?>): Fragment =
    T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }