package com.jungle.board.service;

import com.jungle.board.domain.ChatRoom;
import com.jungle.board.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> getAllChatRooms(Long memberId) {
        return chatRoomRepository.findAllChatRoom(memberId);
    }

    public ChatRoom createChatRoom(String roomName, String description,Long memberId){
        return chatRoomRepository.insertChatRoom(roomName, description, memberId);
    }

    public ChatRoom findChatRoom(Long chatId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(chatId);

        if (chatRoom == null) {
            throw new IllegalArgumentException("채팅방이 존재하지 않거나 접근 권한이 없습니다.");
        }

        return chatRoom;
    }
    

}