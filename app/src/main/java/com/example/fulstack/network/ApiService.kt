package com.example.fulstack.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts/1")
    fun getPost(): Call<Any>

}