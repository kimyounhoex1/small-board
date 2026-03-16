package com.jungle.board.dto;

import com.jungle.board.domain.ChatRoom;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomResponse {
    private Long id;
    private String roomName;
    private String description;
    private Long createdBy;
    private String createdByNickname;
    private Date createdAt;

    public static ChatRoomResponse convert(ChatRoom chatRoom) {
        return convert(chatRoom, null);
    }

    public static ChatRoomResponse convert(ChatRoom chatRoom, String createdByNickname) {
        return new ChatRoomResponse(
            chatRoom.getRoomId(),
            chatRoom.getRoomName(),
            chatRoom.getDescription(),
            chatRoom.getCreatedBy(),
            createdByNickname,
            chatRoom.getCreatedAt()
        );
    }
}