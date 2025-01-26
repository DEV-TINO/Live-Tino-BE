package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.DeleteBroadcastParticipantDAOBean;
import com.example.live_tino.broadcast.bean.small.GetBroadcastParticipantDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastParticipantDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteBroadcastParticipantBean {

    GetBroadcastParticipantDAOBean getBroadcastParticipantDAOBean;
    DeleteBroadcastParticipantDAOBean deleteBroadcastParticipantDAOBean;

    @Autowired
    public DeleteBroadcastParticipantBean(GetBroadcastParticipantDAOBean getBroadcastParticipantDAOBean, DeleteBroadcastParticipantDAOBean deleteBroadcastParticipantDAOBean){
        this.getBroadcastParticipantDAOBean = getBroadcastParticipantDAOBean;
        this.deleteBroadcastParticipantDAOBean = deleteBroadcastParticipantDAOBean;
    }

    public UUID exec(RequestBroadcastParticipantDeleteDTO requestBroadcastParticipantDeleteDTO){
        UUID broadcastParticipantId = requestBroadcastParticipantDeleteDTO.getBroadcastParticipantId();

        BroadcastParticipantDAO broadcastParticipantDAO = getBroadcastParticipantDAOBean.exec(broadcastParticipantId);
        if (broadcastParticipantDAO == null) return null;

        deleteBroadcastParticipantDAOBean.exec(broadcastParticipantDAO);

        return broadcastParticipantId;
    }
}
