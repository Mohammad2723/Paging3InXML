package com.github.ebrahimi16153.paging3inxml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.paging3inxml.databinding.LoadMoreBinding

class LoadMoreAdapter(private val setOnItemRetryClick:() -> Unit) : LoadStateAdapter<LoadMoreAdapter.ViewHolder>() {

    private lateinit var binding: LoadMoreBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadMoreAdapter.ViewHolder {

        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(setOnItemRetryClick = setOnItemRetryClick)

    }

    override fun onBindViewHolder(holder: LoadMoreAdapter.ViewHolder, loadState: LoadState) {

        holder.bindData(loadState = loadState)

    }


    inner class ViewHolder(setOnItemRetryClick: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
          init {
              binding.loadRetry.setOnClickListener {
                  setOnItemRetryClick()
              }
          }
        fun bindData(loadState: LoadState) {
            binding.apply {
                loadMore.isVisible = loadState is LoadState.Loading
                loadError.isVisible = loadState is LoadState.Error
                loadRetry.isVisible = loadState is LoadState.Error


            }
        }

    }
}