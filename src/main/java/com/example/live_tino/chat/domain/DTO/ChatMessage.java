package com.example.live_tino.chat.domain.DTO;

import lombok.*;

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
