package com.jungle.board.dto;

import com.jungle.board.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomResponse {
    private Long roomId;
    private String roomName;
    private String description;
    private Long createBy;

    public static ChatRoomResponse convert(ChatRoom chatRoom) {
        return new ChatRoomResponse(
            chatRoom.getRoomId(),
            chatRoom.getRoomName(),
            chatRoom.getDescription(),
            chatRoom.getCreateBy()
        );
    }
}