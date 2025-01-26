package com.example.live_tino.broadcast.domain;

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
public class BroadcastDAO {
    @Id
    UUID broadcastId;
    UUID userId;

    String title;
    String totalTime;
    String videoUrl;
    String broadcastPassword;
    String saveDate;
    String thumbnail;

    LocalDateTime createAt;
    LocalDateTime uploadAt;

    RoomType roomSetting;
}
