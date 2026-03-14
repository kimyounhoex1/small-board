package com.jungle.board.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jungle.board.domain.ChatRoom;

@Repository
public class ChatRoomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ChatRoom insertChatRoom(String roomName, String description, Long memberId) {
        String sql = "insert into chat_room "
                + "(roomName, description, memberId)  "
                + "values (?, ?, ?)";
        int update;
        try {
            update = jdbcTemplate.update(sql, roomName, description, memberId);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage() + " 오류 발생");
            throw new RuntimeException(e);
        }

        String getIdSql = "SELECT LAST_INSERT_ID()";
        Long generatedId = jdbcTemplate.queryForObject(getIdSql, Long.class);

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomId(generatedId);
        chatRoom.setRoomName(roomName);
        chatRoom.setDescription(description);
        chatRoom.setCreateBy(memberId);

        return chatRoom;
    }
    
}
