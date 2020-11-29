package ru.eyelog.feature_favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.eyelog.core_common.BackListener
import ru.eyelog.core_common.abstractions.FragmentBase
import ru.eyelog.feature_favorites.R

class FavoriteCatsFragment : FragmentBase(), BackListener {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        TODO("Not yet implemented")
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite_list, container, false)

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        TODO("Not yet implemented")
//
//    }

    override fun onBackClick(): Boolean {
        TODO("Not yet implemented")
    }
}