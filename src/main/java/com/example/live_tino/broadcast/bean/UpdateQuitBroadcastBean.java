package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.GetBroadcastDAOBean;
import com.example.live_tino.broadcast.bean.small.SaveBroadcastDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastQuitUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UpdateQuitBroadcastBean {

    GetBroadcastDAOBean getBroadcastDAOBean;
    SaveBroadcastDAOBean saveBroadcastDAOBean;

    @Autowired
    public UpdateQuitBroadcastBean(GetBroadcastDAOBean getBroadcastDAOBean, SaveBroadcastDAOBean saveBroadcastDAOBean){
        this.getBroadcastDAOBean = getBroadcastDAOBean;
        this.saveBroadcastDAOBean = saveBroadcastDAOBean;
    }

    public UUID exec(RequestBroadcastQuitUpdateDTO requestBroadcastQuitUpdateDTO){
        BroadcastDAO broadcastDAO = getBroadcastDAOBean.exec(requestBroadcastQuitUpdateDTO.getBroadcastId());
        if (broadcastDAO == null) return null;

        broadcastDAO.setBroadcastId(requestBroadcastQuitUpdateDTO.getBroadcastId());
        broadcastDAO.setTotalTime(requestBroadcastQuitUpdateDTO.getTotalTime());
        broadcastDAO.setVideoUrl(requestBroadcastQuitUpdateDTO.getVideoUrl());
        broadcastDAO.setSaveDate(requestBroadcastQuitUpdateDTO.getSaveDate());
        broadcastDAO.setThumbnail(requestBroadcastQuitUpdateDTO.getThumbnail());
        broadcastDAO.setUploadAt(LocalDateTime.now());

        saveBroadcastDAOBean.exec(broadcastDAO);

        return broadcastDAO.getBroadcastId();
    }
}
