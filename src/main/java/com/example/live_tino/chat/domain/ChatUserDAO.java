package com.example.live_tino.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatUserDAO {
    @Id
    UUID chatUserId;

    UUID chatRoomId;
    UUID userId;
}
