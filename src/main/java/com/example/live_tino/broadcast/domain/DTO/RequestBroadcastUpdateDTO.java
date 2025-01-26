package com.example.live_tino.broadcast.domain.DTO;

import com.example.live_tino.broadcast.domain.RoomType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class RequestBroadcastUpdateDTO {
    UUID broadcastId;
    RoomType roomSetting;
    String broadcastPassword;
}
