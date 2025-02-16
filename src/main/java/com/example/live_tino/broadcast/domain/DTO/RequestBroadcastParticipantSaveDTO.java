package com.example.live_tino.broadcast.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestBroadcastParticipantSaveDTO {
    String creatorLoginId;
    UUID viewerUserId;
}
