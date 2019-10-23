package com.javiersc.app.data.repo

import com.javiersc.app.data.datasource.local.GitHubDatabase
import com.javiersc.app.data.datasource.network.GitHubApi
import com.javiersc.app.data.repo.models.Error
import com.javiersc.app.data.repo.models.User
import com.javiersc.resource.Resource
import com.javiersc.resource.extensions.toResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

interface GitHubRepo {
    fun getUsers(): Flow<Resource<List<User>, Error>>
}

@ExperimentalCoroutinesApi
class GitHubRepoImpl(
    private val gitHubApi: GitHubApi,
    private val gitHubDatabase: GitHubDatabase
) : GitHubRepo {
    override fun getUsers(): Flow<Resource<List<User>, Error>> = flow {
        // emit Loading to show a loading indicator
        var usersRes: Resource<List<User>, Error> = Resource.Loading(null)
        emit(usersRes)

        // emit the network resource
        usersRes = gitHubApi.getUsers()
        emit(usersRes)

        // if the network fails, emit the database resource
        if (usersRes !is Resource.Success) {
            val usersFlow: Flow<List<User>> = gitHubDatabase.getUsers()
            val usersResourceFlow: Flow<Resource<List<User>, Error>> =
                usersFlow.toResource { users: List<User> -> Resource.Cache(users) }
            emitAll(usersResourceFlow)
        }
    }
}