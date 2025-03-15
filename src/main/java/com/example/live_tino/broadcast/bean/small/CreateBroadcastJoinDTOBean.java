package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastJoinGetDTO;
import com.example.live_tino.broadcast.repository.BroadcastRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBroadcastJoinDTOBean {

    BroadcastRepositoryJPA broadcastRepositoryJPA;

    @Autowired
    public CreateBroadcastJoinDTOBean(BroadcastRepositoryJPA broadcastRepositoryJPA){
        this.broadcastRepositoryJPA = broadcastRepositoryJPA;
    }

    public ResponseBroadcastJoinGetDTO exec(BroadcastParticipantDAO broadcastParticipantDAO){
        String title = broadcastRepositoryJPA.findTitleByBroadcastId(broadcastParticipantDAO.getBroadcastId());

        ResponseBroadcastJoinGetDTO responseBroadcastJoinGetDTO = new ResponseBroadcastJoinGetDTO();

        responseBroadcastJoinGetDTO.setBroadcastParticipantId(broadcastParticipantDAO.getBroadcastParticipantId());
        responseBroadcastJoinGetDTO.setTitle(title);

        return responseBroadcastJoinGetDTO;
    }

}
