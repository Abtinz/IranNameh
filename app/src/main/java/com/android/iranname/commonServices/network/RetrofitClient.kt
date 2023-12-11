package com.android.iranname.commonServices.network

import com.android.iranname.account.network.SignUpApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://45.156.24.177/"

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val request = originalRequest
                .newBuilder()
//                .addHeader("Authorization", "Bearer your_token_here")
                .method(originalRequest.method,originalRequest.body)
                .build()
            chain.proceed(request)
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val SignUpInstance : SignUpApiService by lazy {
        retrofit.create(SignUpApiService::class.java)
    }
}