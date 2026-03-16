package com.jungle.board.domain;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
    private Long roomId;
    private String roomName;
    private String description;
    private Long createdBy;
    private Date createdAt;
    private Map<String, Long> members = new ConcurrentHashMap<>();
}
