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
public class ChatRoomDAO {
    @Id
    UUID chatRoomId;

    UUID userId;

    Boolean isDelete;

    LocalDateTime createAt;
    LocalDateTime uploadAt;
}
