package com.javiersc.app

import com.javiersc.app.data.repo.models.Error
import com.javiersc.app.data.repo.models.User
import com.javiersc.app.di.modules
import com.javiersc.resource.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject


@ExperimentalCoroutinesApi
suspend fun main() {
    startKoin { modules(modules) }

    DemoApplication().getUsers()
}

class DemoApplication : KoinComponent {

    private val gitHubViewModel by inject<GitHubViewModel>()

    suspend fun getUsers() =
        gitHubViewModel.usersRes.collect { users: Resource<List<User>, Error> ->
            when (users) {
                is Resource.Success -> println(users.resource)
                else -> println(users)
            }
        }

}