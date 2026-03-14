package com.jungle.board.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle.board.domain.ChatRoom;
import com.jungle.board.repository.ChatRoomRepository;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(String roomName, String description, String creator, Long memberId){
        return chatRoomRepository.insertChatRoom(roomName, description, memberId);
    }

    

}