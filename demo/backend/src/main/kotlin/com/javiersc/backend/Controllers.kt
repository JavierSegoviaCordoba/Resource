package com.javiersc.backend

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controllers {

    // Change this annotation or the ResponseEntity.status in the function to return different codes
    // @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/users")
    fun users() = ResponseEntity.status(200).body(DummyData.users)
//    fun users() = ResponseEntity.status(204).body(null)
//    fun users() = ResponseEntity.status(404).body(null)
//    fun users() = Unit

    @GetMapping("/login")
    fun login() = ResponseEntity.status(200).body("access_token")
}