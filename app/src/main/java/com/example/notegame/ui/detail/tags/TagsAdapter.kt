package com.example.notegame.ui.detail.tags

import androidx.recyclerview.widget.DiffUtil
import com.example.core.data.source.remote.response.Store
import com.example.core.data.source.remote.response.TagsItem
import com.example.notegame.R
import com.example.notegame.ui.base.BaseAdapterSecond

class TagsAdapter: BaseAdapterSecond<TagsItem>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutRes: Int
        get() = R.layout.item_list_tags

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TagsItem>(){
            override fun areItemsTheSame(oldItem: TagsItem, newItem: TagsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TagsItem, newItem: TagsItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}