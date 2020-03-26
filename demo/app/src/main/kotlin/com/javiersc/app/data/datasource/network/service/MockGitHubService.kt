package com.javiersc.app.data.datasource.network.service

import com.javiersc.app.data.datasource.network.mockdata.MockData
import com.javiersc.app.data.datasource.network.models.ErrorDTO
import com.javiersc.app.data.datasource.network.models.UserDTO
import com.javiersc.resources.networkResponseRetrofit.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.mock.BehaviorDelegate

internal class MockGitHubService(
    private val delegate: BehaviorDelegate<GitHubService>
) : GitHubService {

    override fun getUsersAsync(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>> {
        return delegate.returningResponse(MockData.userList).getUsersAsync()
    }

    override suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO> {
        return delegate.returningResponse(MockData.userList).getUsers()
    }

    override suspend fun login(): NetworkResponse<String, ErrorDTO> {
        return delegate.returningResponse("auth_token").login()
    }
}