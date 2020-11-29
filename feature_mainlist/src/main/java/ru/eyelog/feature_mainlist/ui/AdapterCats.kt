package ru.eyelog.feature_mainlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cat.view.*
import ru.eyelog.core_common.extensions.loadImage
import ru.eyelog.core_data.models.CatPhoto
import ru.eyelog.feature_mainlist.R
import javax.inject.Inject

class AdapterCats @Inject constructor() :
        RecyclerView.Adapter<AdapterCats.CatsViewHolder>() {

    private var catsList: List<CatPhoto> = emptyList()

    fun setData(catsList: List<CatPhoto>) {
        this.catsList = catsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder =
            CatsViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_cat,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) = holder.bind(catsList[position])

    override fun getItemCount() = catsList.size

    inner class CatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CatPhoto) {
            itemView.apply {
                with(item) {
                    ivCat.loadImage(url, R.drawable.empty_cat)
                }
            }
        }
    }
}
