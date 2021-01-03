package com.example.vipercodechallengegithubsearch.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder  {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiService : RepoSearchService by lazy {
        retrofitBuilder
            .build()
            .create(RepoSearchService::class.java)
    }
}