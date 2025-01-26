package com.example.live_tino.broadcast.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseBroadcastsGetDTO {
    UUID broadcastId;

    String title;
    String saveDate;
    String totalTime;
    String thumbnail;

}
