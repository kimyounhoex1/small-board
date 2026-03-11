package com.jungle.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 최초로 접속할 WebSocket 엔드포인트
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("http://localhost:3000", "http://127.0.0.1:3000")
            .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 클라이언트 -> 서버 컨트롤러
        /* 클라이언트가 /app으로 보내는 메세지는 spring controller로, @Controller 안에 @MessageMapping
         * 클라이언트가 /app/chat.send로 보내면, @MessageMapping("/chat.send")으로 라우팅
         * client -> /app/chat.send -> Controller
         */
        config.setApplicationDestinationPrefixes("/app");
        // 클라이언트 -> 브로커 -> 다른 클라이언트
        // 
        config.enableSimpleBroker("/topic", "/queue");
    }

    

    
}
