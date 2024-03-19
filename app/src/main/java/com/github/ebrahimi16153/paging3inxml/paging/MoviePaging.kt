package com.github.ebrahimi16153.paging3inxml.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.ebrahimi16153.paging3inxml.models.ResponseOfMovie
import com.github.ebrahimi16153.paging3inxml.repository.MovieRepository
import javax.inject.Inject

class MoviePaging @Inject constructor(private val repository: MovieRepository):PagingSource<Int,ResponseOfMovie.Data>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseOfMovie.Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseOfMovie.Data> {
      return try {
          val currentPage = params.key ?: 1
          val response = repository.getMovieList(currentPage)

          // if api fail -> must return a emptyList like blow
          val data  = response.body()?.data ?: emptyList()
          val responseData = mutableListOf<ResponseOfMovie.Data>()
          responseData.addAll(data)

          LoadResult.Page(data = responseData, prevKey = if (currentPage == 1) null else -1, nextKey = currentPage.plus(+1))

      }catch (e:Exception){
          LoadResult.Error(e)
      }

    }
}