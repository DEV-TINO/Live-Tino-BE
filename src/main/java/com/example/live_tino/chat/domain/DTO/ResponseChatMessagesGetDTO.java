package com.example.live_tino.chat.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseChatMessagesGetDTO {
    UUID chatMessageId;
    UUID chatRoomId;
    UUID userId;

    String chatMessage;

}
