-- 채팅방 테이블 생성
CREATE TABLE chat_room (
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL,
    description TEXT,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES member(id)
);

CREATE INDEX idx_chat_room_created_by ON chat_room(created_by);
CREATE INDEX idx_chat_room_created_at ON chat_room(created_at);