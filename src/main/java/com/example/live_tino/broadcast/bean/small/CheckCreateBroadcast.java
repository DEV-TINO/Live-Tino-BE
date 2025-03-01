package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastSaveDTO;
import com.example.live_tino.broadcast.repository.BroadcastRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckCreateBroadcast {

    BroadcastRepositoryJPA broadcastRepositoryJPA;

    @Autowired
    public CheckCreateBroadcast(BroadcastRepositoryJPA broadcastRepositoryJPA){
        this.broadcastRepositoryJPA = broadcastRepositoryJPA;
    }

    public Boolean exec(RequestBroadcastSaveDTO requestBroadcastSaveDTO){
        return broadcastRepositoryJPA.existsByUserIdAndIsEndedFalse(requestBroadcastSaveDTO.getUserId());
    }
}
