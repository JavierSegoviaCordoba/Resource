package com.javiersc.backend

import com.javiersc.backend.models.UserDTO

object DummyData {
    val users = listOf(
        UserDTO(1, "Pepe", 23),
        UserDTO(2, "Maria", 28),
        UserDTO(3, "Ana", 7)
    )
    val error = object {
        val message = "Wops, there was an error"
    }
}