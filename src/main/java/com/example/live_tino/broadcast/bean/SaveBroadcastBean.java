package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.CheckCreateBroadcast;
import com.example.live_tino.broadcast.bean.small.CreateBroadcastDAOBean;
import com.example.live_tino.broadcast.bean.small.SaveBroadcastDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveBroadcastBean {

    CreateBroadcastDAOBean createBroadcastDAOBean;
    SaveBroadcastDAOBean saveBroadcastDAOBean;
    CheckCreateBroadcast checkCreateBroadcast;

    @Autowired
    public SaveBroadcastBean(CreateBroadcastDAOBean createBroadcastDAOBean, SaveBroadcastDAOBean saveBroadcastDAOBean, CheckCreateBroadcast checkCreateBroadcast){
        this.createBroadcastDAOBean = createBroadcastDAOBean;
        this.saveBroadcastDAOBean = saveBroadcastDAOBean;
        this.checkCreateBroadcast = checkCreateBroadcast;
    }

    public UUID exec(RequestBroadcastSaveDTO requestBroadcastSaveDTO){

        BroadcastDAO broadcastDAO = createBroadcastDAOBean.exec(requestBroadcastSaveDTO);
        if (broadcastDAO == null) return null;

        saveBroadcastDAOBean.exec(broadcastDAO);

        return broadcastDAO.getBroadcastId();
    }

}
