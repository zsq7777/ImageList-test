package com.able.imagelist.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private const val BASE_URL = "https://gank.io/api/v2/data/category/Girl/type/"

    //    private val authInterceptor = Interceptor { chain ->
//        val newUrl = chain.request().url()
//            .newBuilder()
//            .build()
//
//        val newRequest = chain.request()
//            .newBuilder()
//            .url(newUrl)
//            .build()
//
//        chain.proceed(newRequest)
//    }
    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private val myOkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(
            myOkHttpClient
        )
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiService: ApiService = getRetrofit()
        .create(
        ApiService::class.java)

}