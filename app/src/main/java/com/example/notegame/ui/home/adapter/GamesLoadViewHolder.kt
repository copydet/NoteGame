package com.example.notegame.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.notegame.R
import com.example.notegame.databinding.LoadFooterBinding

class GamesLoadViewHolder(
    private val loadFooterBinding: LoadFooterBinding,
    retry: () -> Unit): RecyclerView.ViewHolder(loadFooterBinding.root) {
    init {
        loadFooterBinding.retryButton.setOnClickListener{ retry.invoke() }
    }

    fun bind(loadState: LoadState){
        loadFooterBinding.progressBar.isVisible = loadState is LoadState.Loading
        loadFooterBinding.retryButton.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): GamesLoadViewHolder{
            return GamesLoadViewHolder(
                LoadFooterBinding.bind(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.load_footer, parent, false)
                ),
                retry
            )
        }
    }
}