package com.javiersc.app.data.datasource.local.mappers

import com.javiersc.app.data.datasource.local.models.UserEntity
import com.javiersc.app.data.repo.models.User

fun List<UserEntity>.toUsers(): List<User> = this.map { userEntity -> userEntity.toUser() }
fun UserEntity.toUser(): User = User(this.id, this.name, this.age)