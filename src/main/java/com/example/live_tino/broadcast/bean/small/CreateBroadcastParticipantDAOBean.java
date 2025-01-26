package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastParticipantSaveDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateBroadcastParticipantDAOBean {

    public BroadcastParticipantDAO exec(RequestBroadcastParticipantSaveDTO requestBroadcastParticipantSaveDTO){

        return BroadcastParticipantDAO.builder()
                .broadcastParticipantId(UUID.randomUUID())
                .broadcastId(requestBroadcastParticipantSaveDTO.getBroadcastId())
                .userId(requestBroadcastParticipantSaveDTO.getUserId())
                .nickname(requestBroadcastParticipantSaveDTO.getNickname())
                .isMute(requestBroadcastParticipantSaveDTO.getIsMute())
                .isDelete(false)
                .createAt(LocalDateTime.now())
                .uploadAt(LocalDateTime.now())
                .build();
    }
}
