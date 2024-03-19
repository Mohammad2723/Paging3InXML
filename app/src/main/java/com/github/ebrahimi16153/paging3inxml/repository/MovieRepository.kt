package com.github.ebrahimi16153.paging3inxml.repository

import com.github.ebrahimi16153.paging3inxml.models.api.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(page:Int) = apiService.movieList(page = page)

}