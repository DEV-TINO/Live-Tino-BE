package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.CreateBroadcastsDTOBean;
import com.example.live_tino.broadcast.bean.small.GetBroadcastsDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetBroadcastsBean {

    GetBroadcastsDAOBean getBroadcastsDAOBean;
    CreateBroadcastsDTOBean createBroadcastsDTOBean;

    @Autowired
    public GetBroadcastsBean(GetBroadcastsDAOBean getBroadcastsDAOBean, CreateBroadcastsDTOBean createBroadcastsDTOBean){
        this.getBroadcastsDAOBean = getBroadcastsDAOBean;
        this.createBroadcastsDTOBean = createBroadcastsDTOBean;
    }

    public Page<ResponseBroadcastsGetDTO> exec(UUID userId, Pageable pageable){
        Page<BroadcastDAO> broadcastDAOPage = getBroadcastsDAOBean.exec(pageable);
        if (broadcastDAOPage.isEmpty()) return null;

        return createBroadcastsDTOBean.exec(broadcastDAOPage, userId, pageable);
    }

}
