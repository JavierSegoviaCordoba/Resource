package com.javiersc.app.data.datasource.local

import com.javiersc.app.data.datasource.local.mappers.toUser
import com.javiersc.app.data.datasource.local.models.UserEntity
import com.javiersc.app.data.repo.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface GitHubDatabase {
    fun getUsers(): Flow<List<User>>
}

class GitHubDatabaseImpl : GitHubDatabase {

    override fun getUsers(): Flow<List<User>> = flow {
        users.map { userEntity: UserEntity -> userEntity.toUser() }
    }
}

private val users: List<UserEntity> = listOf(
    UserEntity(1, "Veronica", 21),
    UserEntity(2, "Pablo", 16)
)