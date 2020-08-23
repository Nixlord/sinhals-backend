package com.pravega.backend.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {
    @GetMapping("/api")
    fun helloAPI() = hashMapOf("json" to "Welcome to Pravega")
}