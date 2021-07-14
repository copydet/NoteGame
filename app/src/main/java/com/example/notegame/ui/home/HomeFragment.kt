package com.example.notegame.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Games
import com.example.notegame.R
import com.example.notegame.databinding.FragmentHomeBinding
import com.example.notegame.ui.base.BaseFragment
import com.example.notegame.ui.home.adapter.GamesHomeAdapter
import com.example.notegame.ui.home.adapter.GamesLoadAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.contracts.contract

@ExperimentalCoroutinesApi
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel : HomeViewModel by viewModel()
    private val homeAdapter by lazy { GamesHomeAdapter { navigateToDetail(it)} }
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        subscribeUi()
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewGames.apply {
                adapter = this@HomeFragment.homeAdapter.withLoadStateHeaderAndFooter(
                    header = GamesLoadAdapter { homeAdapter.retry() },
                    footer = GamesLoadAdapter { homeAdapter.retry() }
                )
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun subscribeUi(){
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.games.collectLatest {
                homeAdapter.submitData(it)
            }
        }
    }

    private fun navigateToDetail(games: Games){
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToDetailFragment(games)
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        job?.cancel()
    }
}