package com.example.notegame.ui.home.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class GamesLoadAdapter(private val retry: () -> Unit): LoadStateAdapter<GamesLoadViewHolder>(){
    override fun onBindViewHolder(holder: GamesLoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): GamesLoadViewHolder {
        return GamesLoadViewHolder.create(parent, retry)
    }
}