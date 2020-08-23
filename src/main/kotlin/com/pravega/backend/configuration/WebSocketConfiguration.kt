package com.pravega.backend.configuration

import com.pravega.backend.controllers.WebSocketHandler
import com.pravega.backend.controllers.interceptors.WebSocketInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.server.HandshakeInterceptor

@Configuration
@EnableWebSocket
class WebSocketConfiguration: WebSocketConfigurer {

    @Autowired
    private lateinit var webSocketHandler: WebSocketHandler

    @Autowired
    private lateinit var webSocketInterceptor: WebSocketInterceptor

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler, "/ws")
                .addInterceptors(webSocketInterceptor)
    }

    @Bean
    fun handler() = WebSocketHandler()

    @Bean
    fun interceptor() = WebSocketInterceptor()
}
