package com.example.notegame.ui.detail.platform

import androidx.recyclerview.widget.DiffUtil
import com.example.core.data.source.remote.response.Platform
import com.example.notegame.R
import com.example.notegame.ui.base.BaseAdapterSecond

class PlatformAdapter: BaseAdapterSecond<Platform>(diffUtil = DIFF_CALLBACK) {
    override val getLayoutRes: Int
        get() = R.layout.item_list_platform

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Platform>(){
            override fun areItemsTheSame(oldItem: Platform, newItem: Platform): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Platform, newItem: Platform): Boolean {
                return oldItem.platform == newItem.platform
            }
        }
    }
}