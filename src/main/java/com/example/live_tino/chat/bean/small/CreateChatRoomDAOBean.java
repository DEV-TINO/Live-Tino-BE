package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatRoomDAO;
import com.example.live_tino.chat.domain.DTO.RequestChatRoomCreateDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateChatRoomDAOBean {

    public ChatRoomDAO exec(RequestChatRoomCreateDTO requestChatRoomCreateDTO){
        return ChatRoomDAO.builder()
                .chatRoomId(UUID.randomUUID())
                .userId(requestChatRoomCreateDTO.getUserId())
                .isDelete(false)
                .createAt(LocalDateTime.now())
                .uploadAt(LocalDateTime.now())
                .build();
    }
}
