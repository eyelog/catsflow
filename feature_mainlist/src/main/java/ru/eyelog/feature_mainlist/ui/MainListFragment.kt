package ru.eyelog.feature_mainlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.eyelog.core_common.BackListener
import ru.eyelog.core_common.abstractions.FragmentBase
import ru.eyelog.core_common.extensions.findComponentDependencies
import ru.eyelog.feature_mainlist.R
import ru.eyelog.feature_mainlist.ViewModelMainList
import ru.eyelog.feature_mainlist.di.DaggerComponentMainList
import timber.log.Timber
import javax.inject.Inject

class MainListFragment: FragmentBase(), BackListener {

    @Inject
    lateinit var viewModelMainList: ViewModelMainList

    @Inject
    lateinit var adapterCats: AdapterCats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerComponentMainList.
                builder()
                .withDependencies(findComponentDependencies())
                .withActivity(activity!!)
                .withFragment(this)
                .build()
                .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.i("Logcat MainListFragment created")

        viewModelMainList.searchCats()

        viewModelMainList.catsLiveData.observeForever {
            adapterCats.setData(it)
            adapterCats.notifyDataSetChanged()
        }
    }

    override fun onBackClick(): Boolean {
        TODO("Not yet implemented")
    }
}