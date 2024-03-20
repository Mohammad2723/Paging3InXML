package com.github.ebrahimi16153.paging3inxml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.ebrahimi16153.paging3inxml.paging.MoviePaging
import com.github.ebrahimi16153.paging3inxml.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {


    val movieList = Pager(PagingConfig(1)) {
        MoviePaging(repository = repository)
    }.flow.cachedIn(viewModelScope )


}