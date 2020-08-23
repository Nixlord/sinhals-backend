package com.pravega.backend.controllers.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class IndexController {
    @GetMapping("/")
    @ResponseBody
    fun homePage() = "Welcome to Pravega"
}