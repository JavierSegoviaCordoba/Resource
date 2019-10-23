package com.javiersc.app.data.datasource.network.service

import com.javiersc.app.data.datasource.network.mockdata.MockData
import com.javiersc.app.data.datasource.network.models.ErrorDTO
import com.javiersc.app.data.datasource.network.models.UserDTO
import com.javiersc.resource.network.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.mock.BehaviorDelegate

internal class MockGitHubService(private val delegate: BehaviorDelegate<GitHubService>) :
    GitHubService {

    override fun getUsersAsync(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>> =
        delegate.returningResponse(MockData.userList).getUsersAsync()

    override suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO> =
//        delegate.returningResponse(MockData.userList).getUsers() // not compatible yet
        NetworkResponse.Success.OK(MockData.userList, null)

}