package com.example.notegame.ui.detail.sreenshot

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.data.source.remote.response.ShortScreenshotsItem
import com.example.core.data.source.remote.response.Store
import com.example.core.domain.model.ScreenShots
import com.example.notegame.R
import com.example.notegame.databinding.ItemListScreenshootBinding
import com.example.notegame.ui.base.BaseAdapterSecond

class ScreenShootAdapter: RecyclerView.Adapter<ScreenShootAdapter.ListViewHolder>(){

    private var listData = ArrayList<ScreenShots>()
    var onClick : ((Int) -> Unit)? = null

    fun setData(newListData: List<ScreenShots>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemListScreenshootBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int =
        listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder){
            with(listData[position]){
                Glide.with(itemView.context)
                    .load(image)
                    .into(binding.screenShootsGame)
            }
        }
    }

    inner class ListViewHolder(val binding: ItemListScreenshootBinding): RecyclerView.ViewHolder(binding.root)
}