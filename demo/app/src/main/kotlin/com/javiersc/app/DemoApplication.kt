package com.javiersc.app

import com.javiersc.app.data.repo.models.Error
import com.javiersc.app.data.repo.models.User
import com.javiersc.app.di.modules
import com.javiersc.resource.Resource
import com.javiersc.resource.extensions.fold
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

    suspend fun getUsers() {
        gitHubViewModel.usersRes.collect { usersResource: Resource<List<User>, Error> ->
            usersResource.fold {
                loading { println("Show loading indicator") }
                noLoading { println("Hide loading indicator") }
                success { users: List<User> -> println("Success: $users") }
                error { error: Error -> println("Error: $error") }
                cache { users: List<User> -> println("Cache: $users") }
            }
        }
    }
}