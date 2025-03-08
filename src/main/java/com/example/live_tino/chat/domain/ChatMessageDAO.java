package com.example.live_tino.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessageDAO {
    @Id
    UUID chatMessageId;

    UUID chatRoomId;
    UUID userId;

    String message;

    LocalDateTime createAt = LocalDateTime.now();
    LocalDateTime uploadAt = LocalDateTime.now();
}
