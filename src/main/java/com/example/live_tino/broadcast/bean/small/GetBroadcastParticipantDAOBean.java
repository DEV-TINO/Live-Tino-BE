package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.repository.BroadcastParticipantRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetBroadcastParticipantDAOBean {

    BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA;

    @Autowired
    public GetBroadcastParticipantDAOBean(BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA){
        this.broadcastParticipantRepositoryJPA = broadcastParticipantRepositoryJPA;
    }

    public BroadcastParticipantDAO exec(UUID broadcastParticipantId){
        return broadcastParticipantRepositoryJPA.findById(broadcastParticipantId).orElse(null);
    }
}
