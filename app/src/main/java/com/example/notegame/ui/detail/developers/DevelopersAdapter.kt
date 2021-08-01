package com.example.notegame.ui.detail.developers

import androidx.recyclerview.widget.DiffUtil
import com.example.core.data.source.remote.response.DevelopersItem
import com.example.notegame.R
import com.example.notegame.ui.base.BaseAdapterSecond

class DevelopersAdapter: BaseAdapterSecond<DevelopersItem>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutRes: Int
        get() = R.layout.item_list_developers

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DevelopersItem>(){
            override fun areItemsTheSame(oldItem: DevelopersItem, newItem: DevelopersItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DevelopersItem, newItem: DevelopersItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}