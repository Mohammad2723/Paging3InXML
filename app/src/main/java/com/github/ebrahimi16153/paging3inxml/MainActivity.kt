package com.github.ebrahimi16153.paging3inxml

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.paging3inxml.adapter.LoadMoreAdapter
import com.github.ebrahimi16153.paging3inxml.adapter.MovieAdapter
import com.github.ebrahimi16153.paging3inxml.databinding.ActivityMainBinding
import com.github.ebrahimi16153.paging3inxml.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //viewModel
    private val viewModel: MovieViewModel by viewModels()

    //adapter
    @Inject
    lateinit var movieAdapter: MovieAdapter

    //binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            //load data
            lifecycleScope.launch {
                viewModel.movieList.collect {
                    movieAdapter.submitData(it)
                }
            }
            lifecycleScope.launch {
                movieAdapter.loadStateFlow.collect {
                    val state = it.refresh

                    // LoadState  is feature of PagingDataAdapter
                    loading.isVisible = state is LoadState.Loading
                }
            }

            //recycler
            recyclerView.apply {

                adapter = movieAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)

            }

            // swipe to refresh
            swipeToRefresh.setOnRefreshListener {
                swipeToRefresh.isRefreshing = false
                movieAdapter.refresh()
            }

            //loadMore

            recyclerView.adapter = movieAdapter.withLoadStateFooter(
                LoadMoreAdapter(setOnItemRetryClick = {

                    movieAdapter.retry()

                })
            )

        }
    }
}