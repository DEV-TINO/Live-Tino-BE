package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.CreateBroadcastParticipantsDTOBean;
import com.example.live_tino.broadcast.bean.small.GetBroadcastParticipantsDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastParticipantGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetParticipantsBean {

    GetBroadcastParticipantsDAOBean getBroadcastParticipantsDAOBean;
    CreateBroadcastParticipantsDTOBean createBroadcastParticipantsDTOBean;

    @Autowired
    public GetParticipantsBean(GetBroadcastParticipantsDAOBean getBroadcastParticipantsDAOBean, CreateBroadcastParticipantsDTOBean createBroadcastParticipantsDTOBean){
        this.getBroadcastParticipantsDAOBean = getBroadcastParticipantsDAOBean;
        this.createBroadcastParticipantsDTOBean = createBroadcastParticipantsDTOBean;
    }

    public List<ResponseBroadcastParticipantGetDTO> exec(UUID broadcastId){
        List<BroadcastParticipantDAO> broadcastParticipantDAOList = getBroadcastParticipantsDAOBean.exec();
        if (broadcastParticipantDAOList.isEmpty()) return null;

        return createBroadcastParticipantsDTOBean.exec(broadcastParticipantDAOList, broadcastId);
    }
}
