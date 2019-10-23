package com.javiersc.app.data.datasource.network.mappers

import com.javiersc.app.data.datasource.network.models.ErrorDTO
import com.javiersc.app.data.datasource.network.models.UserDTO
import com.javiersc.app.data.repo.models.Error
import com.javiersc.app.data.repo.models.User

fun List<UserDTO>.toUsers(): List<User> = this.map { userDTO -> userDTO.toUser() }
fun UserDTO.toUser(): User = User(this.id, this.name, this.age)

fun ErrorDTO.toError(): Error = Error(this.message)