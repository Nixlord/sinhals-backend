package com.pravega.backend.controllers.websocket

import com.pravega.backend.models.greeting.GreetingRequest
import com.pravega.backend.models.greeting.GreetingResponse
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

/**
 * This will not work.
 * Until Tinder/Scarlet or anything equivalent supports STOMP over websockets, we will be using pusher.
 * We may build our own implementation in either of the following two ways after MVP
 * 1: Implement STOMP in Scarlet
 * 2: Implement messaging protocol on SpringBoot
 */
@RestController
class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: GreetingRequest): GreetingResponse {
        return GreetingResponse("Hello ${message.name}");
    }
}
