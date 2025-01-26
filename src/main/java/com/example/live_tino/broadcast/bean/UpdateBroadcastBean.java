package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.GetBroadcastDAOBean;
import com.example.live_tino.broadcast.bean.small.SaveBroadcastDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UpdateBroadcastBean {

    GetBroadcastDAOBean getBroadcastDAOBean;
    SaveBroadcastDAOBean saveBroadcastDAOBean;

    @Autowired
    public UpdateBroadcastBean(GetBroadcastDAOBean getBroadcastDAOBean, SaveBroadcastDAOBean saveBroadcastDAOBean){
        this.getBroadcastDAOBean = getBroadcastDAOBean;
        this.saveBroadcastDAOBean = saveBroadcastDAOBean;
    }

    public UUID exec(RequestBroadcastUpdateDTO requestBroadcastUpdateDTO){
        BroadcastDAO broadcastDAO = getBroadcastDAOBean.exec(requestBroadcastUpdateDTO.getBroadcastId());
        if (broadcastDAO == null) return null;

        broadcastDAO.setBroadcastId(requestBroadcastUpdateDTO.getBroadcastId());
        broadcastDAO.setRoomSetting(requestBroadcastUpdateDTO.getRoomSetting());
        broadcastDAO.setBroadcastPassword(requestBroadcastUpdateDTO.getBroadcastPassword());
        broadcastDAO.setUploadAt(LocalDateTime.now());

        saveBroadcastDAOBean.exec(broadcastDAO);

        return broadcastDAO.getBroadcastId();
    }
}
