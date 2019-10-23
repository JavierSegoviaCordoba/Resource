package com.javiersc.backend

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class Controllers {

    // Change this annotation or the ResponseEntity.status in the function to return different codes
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/users")
    fun users() = DummyData.users
//    fun users() = DummyData.error
//    fun users() = DummyData.error
//    fun users() = ResponseEntity.status(404).body(DummyData.error)
//    fun users() = Unit
}