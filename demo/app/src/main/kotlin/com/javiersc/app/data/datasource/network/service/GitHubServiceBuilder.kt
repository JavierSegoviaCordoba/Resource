package com.javiersc.app.data.datasource.network.service

import com.javiersc.app.data.datasource.network.interceptors.Interceptors
import com.javiersc.resource.network.adapter.NetworkResponseCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

internal object GitHubServiceBuilder {

//    private const val baseUrl = "http://localhost:8080"
    private const val baseUrl = "http://blasblalsadlsdas.com"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptors.httpLoggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        client(okHttpClient)
        addCallAdapterFactory(NetworkResponseCallAdapterFactory())
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    val service = retrofit.create<GitHubService>()

    private val networkBehavior: NetworkBehavior = NetworkBehavior.create().apply {
        setDelay(1, TimeUnit.SECONDS)
        setVariancePercent(0)
        setErrorPercent(0)
    }

    private val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(
        networkBehavior
    ).build()
    private val delegate = mockRetrofit.create(
        GitHubService::class.java
    )
    val mockService =
        MockGitHubService(delegate)

}
