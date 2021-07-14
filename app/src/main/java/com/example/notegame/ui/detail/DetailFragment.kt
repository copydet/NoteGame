package com.example.notegame.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notegame.R
import com.example.notegame.databinding.FragmentDetailBinding
import com.example.notegame.ui.base.BaseFragment
import com.example.notegame.ui.detail.genre.GenreAdapter
import com.example.notegame.utils.ext.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail){

    private val viewModel : DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private val genreAdapter by lazy { GenreAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            args = this@DetailFragment.args.games
            recyclerViewGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.genreAdapter
            }
        }
    }

    private fun subscribeUi() {
        val id = args.games.id ?: 0
        viewModel.getDetail(id)
        observe(viewModel.detail){
            genreAdapter.submitList(it.genres)
            binding.description.text = it.description
        }
    }
}