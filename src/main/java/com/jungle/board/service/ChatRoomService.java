package com.jungle.board.service;

import com.jungle.board.domain.ChatRoom;
import com.jungle.board.dto.ChatRoomResponse;
import com.jungle.board.domain.Member;
import com.jungle.board.repository.ChatRoomRepository;
import com.jungle.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    public List<ChatRoomResponse> getAllChatRooms() {
        List<ChatRoom> findAllChatRooms = chatRoomRepository.findAllChatRoom();
        return findAllChatRooms.stream()
            .map(chatRoom -> {
                Member findMember = memberRepository.findMemberById(chatRoom.getCreatedBy());
                String nickname = findMember != null ? findMember.getNickname() : null;
                return ChatRoomResponse.convert(chatRoom, nickname);
            })
            .toList();
    }

    public ChatRoomResponse createChatRoom(String roomName, String description, Long memberId){
        Member findMember = memberRepository.findMemberById(memberId);
        ChatRoom saved = chatRoomRepository.insertChatRoom(roomName, description, memberId);
        String nickname = findMember != null ? findMember.getNickname() : null;
        return ChatRoomResponse.convert(saved, nickname);
    }

    public ChatRoomResponse findChatRoom(Long chatId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(chatId);
        if (chatRoom == null) {
            throw new IllegalArgumentException("채팅방이 존재하지 않거나 접근 권한이 없습니다.");
        }
        Member findMember = memberRepository.findMemberById(chatRoom.getCreatedBy());
        String nickname = findMember != null ? findMember.getNickname() : null;
        return ChatRoomResponse.convert(chatRoom, nickname);
    }
}