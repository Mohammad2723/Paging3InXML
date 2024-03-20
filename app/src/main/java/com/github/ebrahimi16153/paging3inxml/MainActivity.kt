package com.github.ebrahimi16153.paging3inxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.paging3inxml.adapter.MovieAdapter
import com.github.ebrahimi16153.paging3inxml.databinding.ActivityMainBinding
import com.github.ebrahimi16153.paging3inxml.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

            //recycler
            recyclerView.apply {

                adapter = movieAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)

            }

        }
    }
}