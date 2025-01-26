package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.repository.BroadcastParticipantRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBroadcastParticipantDAOBean {

    BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA;

    @Autowired
    public DeleteBroadcastParticipantDAOBean(BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA){
        this.broadcastParticipantRepositoryJPA = broadcastParticipantRepositoryJPA;
    }

    public void exec(BroadcastParticipantDAO broadcastParticipantDAO){
        broadcastParticipantRepositoryJPA.delete(broadcastParticipantDAO);
    }
}
