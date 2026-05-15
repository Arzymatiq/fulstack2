package com.example.fulstack.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        Log.d("INTERCEPTOR", "REQUEST URL: ${request.url}")
        Log.d("INTERCEPTOR", "REQUEST METHOD: ${request.method}")

        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer TOKEN123")
            .build()

        val response = chain.proceed(newRequest)

        Log.d("INTERCEPTOR", "RESPONSE CODE: ${response.code}")

        return response
    }
}