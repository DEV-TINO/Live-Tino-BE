package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.bean.SaveBroadcastParticipantBean;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.repository.BroadcastParticipantRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveBroadcastParticipantDAOBean {

    BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA;

    @Autowired
    public SaveBroadcastParticipantDAOBean(BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA){
        this.broadcastParticipantRepositoryJPA = broadcastParticipantRepositoryJPA;
    }

    public void exec(BroadcastParticipantDAO broadcastParticipantDAO){
        broadcastParticipantRepositoryJPA.save(broadcastParticipantDAO);
    }
}
