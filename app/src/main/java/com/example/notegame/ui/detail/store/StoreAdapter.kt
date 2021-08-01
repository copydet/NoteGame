package com.example.notegame.ui.detail.store

import androidx.recyclerview.widget.DiffUtil
import com.example.core.data.source.remote.response.GenresItem
import com.example.core.data.source.remote.response.Store
import com.example.notegame.R
import com.example.notegame.ui.base.BaseAdapterSecond

class StoreAdapter: BaseAdapterSecond<Store>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutRes: Int
        get() = R.layout.item_list_stores

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Store>(){
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}