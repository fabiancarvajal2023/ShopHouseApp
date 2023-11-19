package com.fabian.mobile.shophouseapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitClient {
    companion object {
        val instance: Retrofit = Retrofit
            .Builder()
            .baseUrl(NetworkConstants.URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}