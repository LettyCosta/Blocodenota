package com.example.blocodenota.data.remote

import com.example.blocodenota.BuildConfig
import retrofit2.http.GET

interface NewsService {

    @GET("top?api_token=${BuildConfig.API_KEY}&locale=us")
   suspend fun fetchTopNews(): NewsResponse

    @GET("all?api_token=${BuildConfig.API_KEY}&language=pt")
    suspend fun fetchAllNews(): NewsResponse
}

