package com.github.ebrahimi16153.paging3inxml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ebrahimi16153.paging3inxml.databinding.MovieItemBinding
import com.github.ebrahimi16153.paging3inxml.models.ResponseOfMovie
import javax.inject.Inject

class MovieAdapter @Inject constructor() :
    PagingDataAdapter<ResponseOfMovie.Data, MovieAdapter.ViewHolder>(diffCallback = differCallBack) {

    private lateinit var binding: MovieItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()

    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.setData(getItem(position)!!)
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ResponseOfMovie.Data) {

            binding.apply {

                binding.movieTitle.text = item.title
                binding.imageView.load(data = item.poster) {
                    crossfade(true)
                    crossfade(500)
                }


            }

        }
    }

    companion object {


        val differCallBack = object : DiffUtil.ItemCallback<ResponseOfMovie.Data>() {
            override fun areItemsTheSame(
                oldItem: ResponseOfMovie.Data,
                newItem: ResponseOfMovie.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseOfMovie.Data,
                newItem: ResponseOfMovie.Data
            ): Boolean {

                return oldItem == newItem

            }

        }


    }


}