package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.CreateBroadcastJoinDTOBean;
import com.example.live_tino.broadcast.bean.small.CreateBroadcastParticipantDAOBean;
import com.example.live_tino.broadcast.bean.small.GetBroadcastDAOBean;
import com.example.live_tino.broadcast.bean.small.SaveBroadcastParticipantDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastParticipantSaveDTO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastJoinGetDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveBroadcastParticipantBean {

    CreateBroadcastParticipantDAOBean createBroadcastParticipantDAOBean;
    SaveBroadcastParticipantDAOBean saveBroadcastParticipantDAOBean;
    GetBroadcastDAOBean getBroadcastDAOBean;
    CreateBroadcastJoinDTOBean createBroadcastJoinDTOBean;

    public SaveBroadcastParticipantBean(CreateBroadcastParticipantDAOBean createBroadcastParticipantDAOBean, SaveBroadcastParticipantDAOBean saveBroadcastParticipantDAOBean, GetBroadcastDAOBean getBroadcastDAOBean, CreateBroadcastJoinDTOBean createBroadcastJoinDTOBean){
        this.createBroadcastParticipantDAOBean = createBroadcastParticipantDAOBean;
        this.saveBroadcastParticipantDAOBean = saveBroadcastParticipantDAOBean;
        this.getBroadcastDAOBean = getBroadcastDAOBean;
        this.createBroadcastJoinDTOBean = createBroadcastJoinDTOBean;
    }

    public ResponseBroadcastJoinGetDTO exec(RequestBroadcastParticipantSaveDTO requestBroadcastParticipantSaveDTO){

        BroadcastParticipantDAO broadcastParticipantDAO = createBroadcastParticipantDAOBean.exec(requestBroadcastParticipantSaveDTO);
        if (broadcastParticipantDAO == null) return null;

        saveBroadcastParticipantDAOBean.exec(broadcastParticipantDAO);

        return createBroadcastJoinDTOBean.exec(broadcastParticipantDAO);
    }
}
