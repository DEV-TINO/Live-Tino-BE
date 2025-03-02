package com.example.live_tino.chat.domain.DTO;

import com.example.live_tino.chat.domain.Type;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    UUID roomId;
    String content;
}
