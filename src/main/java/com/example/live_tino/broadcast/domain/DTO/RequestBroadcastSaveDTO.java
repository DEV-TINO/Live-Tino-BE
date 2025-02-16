package com.example.live_tino.broadcast.domain.DTO;

import com.example.live_tino.broadcast.domain.RoomType;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestBroadcastSaveDTO {

    UUID userId;
    String title;
    RoomType roomSetting;
    String broadcastPassword;

}
