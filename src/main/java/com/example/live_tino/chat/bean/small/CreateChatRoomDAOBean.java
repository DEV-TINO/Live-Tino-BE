package com.example.live_tino.chat.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastAndChatSaveDTO;
import com.example.live_tino.chat.domain.ChatRoomDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateChatRoomDAOBean {

    public ChatRoomDAO exec(RequestBroadcastAndChatSaveDTO requestBroadcastAndChatSaveDTO, BroadcastDAO broadcastDAO){
        return ChatRoomDAO.builder()
                .chatRoomId(broadcastDAO.getBroadcastId())
                .userId(requestBroadcastAndChatSaveDTO.getUserId())
                .isDelete(false)
                .createAt(LocalDateTime.now())
                .uploadAt(LocalDateTime.now())
                .build();
    }
}
