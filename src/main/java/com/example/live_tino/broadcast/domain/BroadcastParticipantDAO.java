package com.example.live_tino.broadcast.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BroadcastParticipantDAO {
    @Id
    UUID broadcastParticipantId;

    UUID broadcastId;
    UUID userId;

    Boolean isDelete;

    LocalDateTime createAt;
    LocalDateTime uploadAt;
}
