package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastSaveDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateBroadcastDAOBean {

    // 방송 DAO 생성
    public BroadcastDAO exec(RequestBroadcastSaveDTO requestBroadcastSaveDTO){

        String password = "";
        if (requestBroadcastSaveDTO.getBroadcastPassword() != null)
            password = requestBroadcastSaveDTO.getBroadcastPassword();

        return BroadcastDAO.builder()
                .broadcastId(UUID.randomUUID())
                .userId(requestBroadcastSaveDTO.getUserId())
                .title(requestBroadcastSaveDTO.getTitle())
                .totalTime("99:99:99")
                .videoUrl("")
                .broadcastPassword(password)
                .saveDate("99.99.99")
                .thumbnail("")
                .createAt(LocalDateTime.now())
                .uploadAt(LocalDateTime.now())
                .roomSetting(requestBroadcastSaveDTO.getRoomSetting())
                .build();
    }
}
