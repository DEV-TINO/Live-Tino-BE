package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.repository.BroadcastRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetBroadcastDAOBean {

    BroadcastRepositoryJPA broadcastRepositoryJPA;

    @Autowired
    public GetBroadcastDAOBean(BroadcastRepositoryJPA broadcastRepositoryJPA){
        this.broadcastRepositoryJPA = broadcastRepositoryJPA;
    }

    public BroadcastDAO exec(UUID broadcastId){
        return broadcastRepositoryJPA.findById(broadcastId).orElse(null);
    }

    public BroadcastDAO exec2(UUID userId){
        return broadcastRepositoryJPA.findByUserId(userId);
    }
}
