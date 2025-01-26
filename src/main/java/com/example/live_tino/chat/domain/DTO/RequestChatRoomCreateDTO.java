package com.example.live_tino.chat.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestChatRoomCreateDTO {
    UUID userId;
}
