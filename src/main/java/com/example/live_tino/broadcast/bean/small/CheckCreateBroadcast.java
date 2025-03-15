package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastAndChatSaveDTO;
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

    public Boolean exec(RequestBroadcastAndChatSaveDTO requestBroadcastAndChatSaveDTO){
        return broadcastRepositoryJPA.existsByUserIdAndIsEndedFalse(requestBroadcastAndChatSaveDTO.getUserId());
    }
}
