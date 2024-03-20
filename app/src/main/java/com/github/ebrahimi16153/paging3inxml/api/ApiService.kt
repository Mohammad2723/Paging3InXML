package com.github.ebrahimi16153.paging3inxml.api

import com.github.ebrahimi16153.paging3inxml.models.ResponseOfMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movies")
    suspend fun movieList(@Query("page") page:Int):Response<ResponseOfMovie>

}