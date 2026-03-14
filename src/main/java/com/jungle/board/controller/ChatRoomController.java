package com.jungle.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.board.config.JwtUtil;
import com.jungle.board.domain.ChatRoom;
import com.jungle.board.domain.Member;
import com.jungle.board.dto.ChatRoomRequest;
import com.jungle.board.dto.ChatRoomResponse;
import com.jungle.board.service.ChatRoomService;
import com.jungle.board.service.MemberService;

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
    @Autowired
    private MemberService memberService;


    @PostMapping("/create")
    public ChatRoom createChatRoom(
        @RequestBody ChatRoomRequest req, 
        HttpServletRequest request) {

        String getToken = request.getHeader("Authorization");
        String creatorNickname = "";
        Long memberId = null;
        if(getToken != null && getToken.startsWith("Bearer ")) {
            String token = getToken.substring(7);
            memberId = Long.parseLong(jwtUtils.getMemberId(token));
            Member member = memberService.findMemberById(memberId);
            creatorNickname = member.getNickname();
            System.out.println("채팅방 생성자 - " + memberId + ", " + creatorNickname);
        }

        ChatRoom created = chatRoomService.createChatRoom(
            req.getRoomName(), 
            req.getDescription(), 
            creatorNickname,
            memberId);
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
