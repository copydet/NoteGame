package com.example.notegame.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any>(
    val onClick: (T) -> Unit = {},
    diffUtil: DiffUtil.ItemCallback<T>
): PagingDataAdapter<T, BaseAdapter<T>.ViewHolder>(diffUtil) {


    inner class ViewHolder(private val binding: ViewDataBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(item: T){
            binding.apply {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }

    private var items: MutableList<T> = mutableListOf()

    protected abstract val getLayoutResId: Int

    private fun onItemClick(item: T) = onClick(item)

    fun submitList(items: List<T>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item = getItem(position) ?: return
        holder.apply {
            itemView.setOnClickListener { onItemClick(item) }
            bind(item)
        }
    }
    override fun getItemViewType(position: Int):Int = getLayoutResId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType,parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int = items.size
}