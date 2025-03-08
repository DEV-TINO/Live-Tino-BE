package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastParticipantSaveDTO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastJoinGetDTO;
import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateBroadcastParticipantDAOBean {

    GetUserDAOBean getUserDAOBean;
    GetBroadcastDAOBean getBroadcastDAOBean;

    @Autowired
    public CreateBroadcastParticipantDAOBean(GetUserDAOBean getUserDAOBean, GetBroadcastDAOBean getBroadcastDAOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.getBroadcastDAOBean = getBroadcastDAOBean;
    }

    public BroadcastParticipantDAO exec(RequestBroadcastParticipantSaveDTO requestBroadcastParticipantSaveDTO){

        UserDAO userDAO = getUserDAOBean.exec(requestBroadcastParticipantSaveDTO.getCreatorLoginId());
        BroadcastDAO broadcastDAO = getBroadcastDAOBean.exec2(userDAO.getUserId());

        return BroadcastParticipantDAO.builder()
                .broadcastParticipantId(UUID.randomUUID())
                .broadcastId(broadcastDAO.getBroadcastId())
                .userId(requestBroadcastParticipantSaveDTO.getViewerUserId())
                .isDelete(false)
                .createAt(LocalDateTime.now())
                .uploadAt(LocalDateTime.now())
                .build();
    }
}
