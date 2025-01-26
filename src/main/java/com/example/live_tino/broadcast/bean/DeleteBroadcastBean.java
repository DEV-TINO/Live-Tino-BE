package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.DeleteBroadcastDAOBean;
import com.example.live_tino.broadcast.bean.small.GetBroadcastDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteBroadcastBean {

    GetBroadcastDAOBean getBroadcastDAOBean;
    DeleteBroadcastDAOBean deleteBroadcastDAOBean;

    @Autowired
    public DeleteBroadcastBean(GetBroadcastDAOBean getBroadcastDAOBean, DeleteBroadcastDAOBean deleteBroadcastDAOBean){
        this.getBroadcastDAOBean = getBroadcastDAOBean;
        this.deleteBroadcastDAOBean = deleteBroadcastDAOBean;
    }

    public UUID exec(RequestBroadcastDeleteDTO requestBroadcastDeleteDTO){
        UUID broadcastId = requestBroadcastDeleteDTO.getBroadcastId();

        BroadcastDAO broadcastDAO = getBroadcastDAOBean.exec(broadcastId);
        if(broadcastDAO==null) return null;

        deleteBroadcastDAOBean.exec(broadcastDAO);

        return broadcastId;
    }
}
