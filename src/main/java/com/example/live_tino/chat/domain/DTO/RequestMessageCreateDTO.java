package com.example.live_tino.chat.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RequestMessageCreateDTO {
    String chatMessage;
}
