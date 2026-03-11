package com.jungle.board.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.jungle.board.messages.ChatMessage;

@Controller
public class ChatController {

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage send(@DestinationVariable String roomId, ChatMessage message) {
        // 필요한 경우 roomId를 사용해 검증/저장 로직 추가
        return message;
    }
}
