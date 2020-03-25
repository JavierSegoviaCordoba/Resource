package com.javiersc.app.data.datasource.network

import com.javiersc.app.data.datasource.network.mappers.toError
import com.javiersc.app.data.datasource.network.mappers.toUsers
import com.javiersc.app.data.datasource.network.models.ErrorDTO
import com.javiersc.app.data.datasource.network.models.UserDTO
import com.javiersc.app.data.datasource.network.service.GitHubService
import com.javiersc.app.data.repo.models.Error
import com.javiersc.app.data.repo.models.User
import com.javiersc.resource.Resource
import com.javiersc.resource.network.extensions.toResource

interface GitHubApi {
    suspend fun getUsersAsync(): Resource<List<User>, Error>
    suspend fun getUsers(): Resource<List<User>, Error>
    suspend fun login(): Resource<String, Error>
}

class GitHubApiImpl(private val gitHubService: GitHubService) : GitHubApi {

    override suspend fun getUsersAsync(): Resource<List<User>, Error> {
        return gitHubService.getUsersAsync().await().toResource(
            mapSuccess = { usersDTO: List<UserDTO> -> usersDTO.toUsers() },
            mapError = { errorDTO: ErrorDTO? -> errorDTO.toError() }
        )
    }

    override suspend fun getUsers(): Resource<List<User>, Error> {
        return gitHubService.getUsers().toResource(
            mapSuccess = { usersDTO: List<UserDTO> -> usersDTO.toUsers() },
            mapError = { errorDTO: ErrorDTO? -> errorDTO.toError() }
        )
    }

    override suspend fun login(): Resource<String, Error> {
        return gitHubService.login().toResource(
            mapSuccess = { token -> token },
            mapError = { errorDTO: ErrorDTO? -> errorDTO.toError() }
        )
    }
}
