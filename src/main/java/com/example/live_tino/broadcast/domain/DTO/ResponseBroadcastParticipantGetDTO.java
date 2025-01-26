package com.example.live_tino.broadcast.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseBroadcastParticipantGetDTO {
    UUID userId;
    String nickname;
}
