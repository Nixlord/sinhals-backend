package com.pravega.backend.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.pravega.backend.controllers.api.friends.Friend
import org.springframework.core.AttributeAccessor
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.integration.core.MessagingTemplate
import org.springframework.integration.websocket.IntegrationWebSocketContainer
import org.springframework.integration.websocket.WebSocketListener
import org.springframework.integration.websocket.inbound.WebSocketInboundChannelAdapter
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import reactor.netty.http.websocket.WebsocketInbound

class WebSocketHandler: TextWebSocketHandler() {
    // Use custom deserializer to diff COMMAND/DATA messages
    private val objectMapper = ObjectMapper().registerModule(KotlinModule())
    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        super.handleTransportError(session, exception)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
//        session.sendMessage(TextMessage(message.payload))
        val friend = objectMapper.readValue(message.payload, Friend::class.java)
        println("WEBSOCKET SERVER");
        println(friend)
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(friend)))
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
    }
}
