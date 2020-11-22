package ru.eyelog.feature_mainlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.eyelog.core_common.BackListener
import ru.eyelog.core_common.abstractions.FragmentBase
import ru.eyelog.feature_mainlist.R

class MainListFragment: FragmentBase(), BackListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TODO("Not yet implemented")
    }

    override fun onBackClick(): Boolean {
        TODO("Not yet implemented")
    }
}