package com.example.notegame.ui.detail.genre

import androidx.recyclerview.widget.DiffUtil
import com.example.core.data.source.remote.response.GenresItem
import com.example.notegame.R
import com.example.notegame.ui.base.BaseAdapterSecond

class GenreAdapter: BaseAdapterSecond<GenresItem>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutRes: Int
        get() = R.layout.item_list_genre

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GenresItem>(){
            override fun areItemsTheSame(oldItem: GenresItem, newItem: GenresItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GenresItem, newItem: GenresItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}