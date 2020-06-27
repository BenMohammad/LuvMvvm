package com.benmohammad.luvmvvm.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    private val API_BASE_URL = "https://obscure-earth-55790.herokuapp.com"

    private var serviceApiInterface: ServiceApiInterface? = null

    fun build(): ServiceApiInterface? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        serviceApiInterface = retrofit.create(
            ServiceApiInterface::class.java
        )

        return serviceApiInterface as ServiceApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServiceApiInterface {
        @GET("/api/museums/")
        fun museums(): Call<MuseumResponse>
    }
}