package com.javiersc.app.data.datasource.network.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(val id: Int, val name: String, val age: Int)
