package com.javiersc.app.data.datasource.network.mockdata

import com.javiersc.app.data.datasource.network.models.UserDTO

object MockData {
    val userList = listOf(
        UserDTO(1, "Juan", 26),
        UserDTO(2, "Laura", 28)
    )
}