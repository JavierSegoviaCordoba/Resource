package com.javiersc.app.data.repo

import com.javiersc.app.data.datasource.local.GitHubDatabase
import com.javiersc.app.data.datasource.network.GitHubApi
import com.javiersc.app.data.repo.models.Error
import com.javiersc.app.data.repo.models.User
import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.ifError
import com.javiersc.resources.resource.extensions.toResourceCache
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

interface GitHubRepo {
    fun getUsers(): Flow<Resource<List<User>, Error>>
    fun signIn(): Flow<Resource<String, Error>>
}

@ExperimentalCoroutinesApi
class GitHubRepoImpl(
    private val gitHubApi: GitHubApi,
    private val gitHubDatabase: GitHubDatabase
) : GitHubRepo {

    override fun getUsers(): Flow<Resource<List<User>, Error>> = flow {
        // emit Loading to show a loading indicator
        emit(Resource.Loading)

        // emit the network resource (Success or Error)
        val usersResource: Resource<List<User>, Error> = gitHubApi.getUsers()
        emit(gitHubApi.getUsers())

        // if the network fails (Error), emit the database flow as Resource Cache
        usersResource.ifError {
            val usersFlow: Flow<List<User>> = gitHubDatabase.getUsers()
            emitAll(usersFlow.toResourceCache())
        }
    }

    override fun signIn(): Flow<Resource<String, Error>> = flow {
        emit(Resource.Loading)
        val loginResource: Resource<String, Error> = gitHubApi.login()
        emit(loginResource)
    }
}
