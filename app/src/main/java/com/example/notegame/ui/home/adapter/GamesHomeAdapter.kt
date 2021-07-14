package com.example.notegame.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.Games
import com.example.notegame.R
import com.example.notegame.ui.base.BaseAdapter

class GamesHomeAdapter(onClick: (Games) -> Unit): BaseAdapter<Games>(diffUtil = itemCompator, onClick = onClick) {
    override val getLayoutResId: Int
        get() = R.layout.item_list_game

    companion object{
        private val itemCompator = object : DiffUtil.ItemCallback<Games>(){
            override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean =
                oldItem == newItem
        }
    }
}