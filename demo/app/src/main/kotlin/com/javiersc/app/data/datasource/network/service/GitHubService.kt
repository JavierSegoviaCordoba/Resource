package com.javiersc.app.data.datasource.network.service

import com.javiersc.app.data.datasource.network.models.ErrorDTO
import com.javiersc.app.data.datasource.network.models.UserDTO
import com.javiersc.resource.network.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface GitHubService {

    @GET("users")
    fun getUsersAsync(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>>

    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO>

    @GET("login")
    suspend fun login(): NetworkResponse<String, ErrorDTO>
}



