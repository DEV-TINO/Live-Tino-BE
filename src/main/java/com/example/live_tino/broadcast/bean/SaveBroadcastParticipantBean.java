package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.CreateBroadcastParticipantDAOBean;
import com.example.live_tino.broadcast.bean.small.SaveBroadcastParticipantDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastParticipantSaveDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveBroadcastParticipantBean {

    CreateBroadcastParticipantDAOBean createBroadcastParticipantDAOBean;
    SaveBroadcastParticipantDAOBean saveBroadcastParticipantDAOBean;

    public SaveBroadcastParticipantBean(CreateBroadcastParticipantDAOBean createBroadcastParticipantDAOBean, SaveBroadcastParticipantDAOBean saveBroadcastParticipantDAOBean){
        this.createBroadcastParticipantDAOBean = createBroadcastParticipantDAOBean;
        this.saveBroadcastParticipantDAOBean = saveBroadcastParticipantDAOBean;
    }

    public UUID exec(RequestBroadcastParticipantSaveDTO requestBroadcastParticipantSaveDTO){

        BroadcastParticipantDAO broadcastParticipantDAO = createBroadcastParticipantDAOBean.exec(requestBroadcastParticipantSaveDTO);
        if (broadcastParticipantDAO == null) return null;

        saveBroadcastParticipantDAOBean.exec(broadcastParticipantDAO);

        return broadcastParticipantDAO.getBroadcastParticipantId();
    }
}
