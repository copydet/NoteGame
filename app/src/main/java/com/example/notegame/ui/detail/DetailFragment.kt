package com.example.notegame.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.source.remote.response.PublishersItem
import com.example.notegame.R
import com.example.notegame.databinding.FragmentDetailBinding
import com.example.notegame.ui.base.BaseFragment
import com.example.notegame.ui.detail.developers.DevelopersAdapter
import com.example.notegame.ui.detail.genre.GenreAdapter
import com.example.notegame.ui.detail.platform.PlatformAdapter
import com.example.notegame.ui.detail.publisher.PublisherAdapter
import com.example.notegame.ui.detail.sreenshot.ScreenShootAdapter
import com.example.notegame.ui.detail.store.StoreAdapter
import com.example.notegame.ui.detail.tags.TagsAdapter
import com.example.notegame.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail){

    private val viewModel : DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private val genreAdapter by lazy { GenreAdapter() }
    private val publisherAdapter by lazy { PublisherAdapter() }
    private val platformAdapter by lazy { PlatformAdapter() }
    private val developersAdapter by lazy { DevelopersAdapter() }
    private val storeAdapter by lazy { StoreAdapter() }
    private val tagsAdapter by lazy { TagsAdapter() }
    private val screenShotsAdapter by lazy { ScreenShootAdapter() }
    private var isShrink = true
    private var job : Job? = null

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
            recyclerViewDevelopers.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.developersAdapter
            }
            recyclerViewPublishers.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.publisherAdapter
            }
            recyclerViewPlatform.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.platformAdapter
            }
            recyclerViewStores.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.storeAdapter
            }
            recyclerViewTags.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.tagsAdapter
            }
            recyclerViewScreenshoot.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = this@DetailFragment.screenShotsAdapter
            }
            backBtn.setOnClickListener { findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToNavigationHome()) }
        }
    }

    private fun subscribeUi() {
        val id = args.games.id ?: 0
        viewModel.getDetail(id)
        viewModel.getScreenShots(id)

        observe(viewModel.detail){
            developersAdapter.submitList(it.developers)
            genreAdapter.submitList(it.genres)
            platformAdapter.submitList(it.platform)
            publisherAdapter.submitList(it.publishers)
            storeAdapter.submitList(it.stores)
            tagsAdapter.submitList(it.tags)

            binding.description.apply {
                setText(it.description)
                setOnStateChangeListener {
                    isShrink = it
                }
                setText(it.description)
                resetState(isShrink)
            }
            binding.website.apply {
                setText(it.website)
            }
        }

        observe(viewModel.screenShots){
            screenShotsAdapter.setData(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        job?.cancel()
    }
}