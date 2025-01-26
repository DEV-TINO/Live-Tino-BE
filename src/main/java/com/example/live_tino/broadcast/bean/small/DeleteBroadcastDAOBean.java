package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.repository.BroadcastRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBroadcastDAOBean {

    BroadcastRepositoryJPA broadcastRepositoryJPA;

    @Autowired
    public DeleteBroadcastDAOBean(BroadcastRepositoryJPA broadcastRepositoryJPA){
        this.broadcastRepositoryJPA = broadcastRepositoryJPA;
    }

    public void exec(BroadcastDAO broadcastDAO){
        broadcastRepositoryJPA.delete(broadcastDAO);
    }
}
