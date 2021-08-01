package com.example.notegame.ui.detail.publisher

import androidx.recyclerview.widget.DiffUtil
import com.example.core.data.source.remote.response.PublishersItem
import com.example.notegame.R
import com.example.notegame.ui.base.BaseAdapterSecond

class PublisherAdapter: BaseAdapterSecond<PublishersItem>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutRes: Int
        get() = R.layout.item_list_publisher

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PublishersItem>(){
            override fun areItemsTheSame(oldItem: PublishersItem, newItem: PublishersItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PublishersItem, newItem: PublishersItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}