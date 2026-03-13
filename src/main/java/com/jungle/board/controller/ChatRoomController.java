package com.jungle.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.config.JwtUtil;
import com.jungle.board.domain.ChatRoom;
import com.jungle.board.dto.ChatRoomRequest;
import com.jungle.board.dto.ChatRoomResponse;
import com.jungle.board.service.ChatRoomService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/chat")
public class ChatRoomController {

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private ChatRoomService chatRoomService;
    


    @PostMapping("/create")
    public ChatRoomResponse createChatRoom(
        @RequestBody ChatRoomRequest req, 
        HttpServletRequest request) {
            
        String getToken = request.getHeader("Authorization");
        String creatorNickname = "";
        Long memberId = null;
        if(getToken != null && getToken.startsWith("Bearer ")) {
            String token = getToken.substring(7);
            creatorNickname = jwtUtils.getNickname(token);
            System.out.println("채팅방 생성자 - " + creatorNickname);
        }

        ChatRoom created = chatRoomService.createChatRoom(request, creatorNickname);
        return created;
    }
    
    @GetMapping()
    public String findChatRoom(@RequestParam String param) {
        return new String();
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
}
