package com.example.fulstack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.network.ApiService
import com.example.fulstack.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api =
            RetrofitClient.retrofit.create(ApiService::class.java)

        api.getPost().enqueue(object : Callback<Any> {

            override fun onResponse(
                call: Call<Any>,
                response: Response<Any>
            ) {

                Log.d("API", "SUCCESS")
            }

            override fun onFailure(
                call: Call<Any>,
                t: Throwable
            ) {

                Log.d("API", "ERROR")
            }
        })
    }
}