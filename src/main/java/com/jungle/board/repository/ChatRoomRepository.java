package com.jungle.board.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.jungle.board.domain.ChatRoom;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<ChatRoom> findAllChatRoom() {
        String sql = "select * from chat_room ";
        List<ChatRoom> getAllChatRooms = null;
        try {
            getAllChatRooms = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(ChatRoom.class)
            );
        } catch (DataAccessException e) {
            System.out.println(e.getMessage() + " 오류 발생");
            throw new RuntimeException(e);
        }

        return getAllChatRooms;
    }

    public ChatRoom insertChatRoom(String roomName, String description, Long createdBy) {
        String sql = "insert into chat_room "
                + "(room_name, description, created_by)  "
                + "values (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, roomName, description, createdBy);
            String getIdSql = "SELECT LAST_INSERT_ID()";
            Long generatedId = jdbcTemplate.queryForObject(getIdSql, Long.class);
            
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomId(generatedId);
            chatRoom.setRoomName(roomName);
            chatRoom.setDescription(description);
            chatRoom.setCreatedBy(createdBy);
            
            return chatRoom;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage() + " 오류 발생");
            throw new RuntimeException(e);
        }

    }

    public ChatRoom findChatRoomById(Long chatId) {
        String sql = "select room_id, room_name, description, created_by"
            + " from chat_room"
            + " where room_id = ?";
        try {
            List<ChatRoom> result = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(ChatRoom.class),
                chatId
            );

            return result.stream().findFirst().orElse(null);
        } catch (DataAccessException e) {
            throw new RuntimeException("채팅방 조회 실패", e);
        }
    }

}
