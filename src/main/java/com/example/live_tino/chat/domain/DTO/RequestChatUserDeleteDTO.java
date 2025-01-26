package com.example.live_tino.chat.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestChatUserDeleteDTO {
    UUID chatRoomId;
    UUID userId;
}
