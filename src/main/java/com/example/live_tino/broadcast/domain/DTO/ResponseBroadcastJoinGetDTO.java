package com.example.live_tino.broadcast.domain.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class ResponseBroadcastJoinGetDTO {
    UUID broadcastParticipantId;
    String title;
}
