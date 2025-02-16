package com.example.live_tino.broadcast.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestBroadcastQuitUpdateDTO {
    UUID broadcastId;
    String videoUrl;
    String thumbnail;
}
