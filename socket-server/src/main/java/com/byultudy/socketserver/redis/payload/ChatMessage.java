package com.byultudy.socketserver.redis.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessage {
    private String sender;
    private String context;
}