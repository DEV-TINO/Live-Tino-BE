package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.repository.BroadcastParticipantRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetBroadcastParticipantsDAOBean {

    BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA;

    @Autowired
    public GetBroadcastParticipantsDAOBean(BroadcastParticipantRepositoryJPA broadcastParticipantRepositoryJPA){
        this.broadcastParticipantRepositoryJPA = broadcastParticipantRepositoryJPA;
    }

    public List<BroadcastParticipantDAO> exec(){
        return broadcastParticipantRepositoryJPA.findAllByOrderByCreateAtDesc();
    }
}
