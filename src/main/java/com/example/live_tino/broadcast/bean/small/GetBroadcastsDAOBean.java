package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.repository.BroadcastRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetBroadcastsDAOBean {

    BroadcastRepositoryJPA broadcastRepositoryJPA;

    @Autowired
    public GetBroadcastsDAOBean(BroadcastRepositoryJPA broadcastRepositoryJPA){
        this.broadcastRepositoryJPA = broadcastRepositoryJPA;
    }

    public List<BroadcastDAO> exec(){
        return broadcastRepositoryJPA.findAllByOrderByCreateAtAsc();
    }
}
