package com.example.live_tino.broadcast.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestBroadcastParticipantSaveDTO {
    UUID broadcastId;
    UUID userId;
    String nickname;
    Boolean isMute;
}
