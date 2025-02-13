package com.example.live_tino.chat.domain.DTO;

import com.example.live_tino.chat.domain.Type;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
public class ChatMessage {
    UUID roomId;
    String content;
}
