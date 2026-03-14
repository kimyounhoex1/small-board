package com.jungle.board.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
    private Long roomId;
    private String roomName;
    private String description;

    private Long createBy;
    private Map<String, Long> members = new ConcurrentHashMap<>();
}
