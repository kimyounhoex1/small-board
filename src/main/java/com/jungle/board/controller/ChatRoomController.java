package com.jungle.board.controller;

import com.jungle.board.config.JwtUtil;
import com.jungle.board.domain.ChatRoom;
import com.jungle.board.dto.ChatRoomRequest;
import com.jungle.board.dto.ChatRoomResponse;
import com.jungle.board.service.ChatRoomService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final JwtUtil jwtUtils;
    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public List<ChatRoomResponse> getAllChatRooms(HttpServletRequest request) {
        Long memberId = extractMemberId(request);

        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms(memberId);
        List<ChatRoomResponse> result = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            result.add(ChatRoomResponse.convert(chatRoom));
        }
        return result;
    }

    @PostMapping("/create")
    public ChatRoomResponse createChatRoom(
        @RequestBody ChatRoomRequest req, 
        HttpServletRequest request) {
        Long memberId = extractMemberId(request);

        ChatRoom created = chatRoomService.createChatRoom(
            req.getRoomName(),
            req.getDescription(),
            memberId)
        ;
        return ChatRoomResponse.convert(created);
    }

    // api/chat/${chatId} // 채팅방 ID로 찾을거임
    @GetMapping("/{chatId}")
    public ChatRoomResponse getMemberChatRoom(
        @PathVariable Long chatId,
        HttpServletRequest request) {

        ChatRoom chatRoom = chatRoomService.findChatRoom(chatId);
        return ChatRoomResponse.convert(chatRoom);
    }

    private Long extractMemberId(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new IllegalArgumentException("유효한 Authorization 헤더가 없습니다.");
        }

        String token = authorization.substring(7);
        return Long.parseLong(jwtUtils.getMemberId(token));
    }
}
