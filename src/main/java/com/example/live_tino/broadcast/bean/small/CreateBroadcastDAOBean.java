package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateBroadcastDAOBean {

    CheckCreateBroadcast checkCreateBroadcast;

    @Autowired
    public CreateBroadcastDAOBean(CheckCreateBroadcast checkCreateBroadcast){
        this.checkCreateBroadcast = checkCreateBroadcast;
    }

    // 방송 DAO 생성
    public BroadcastDAO exec(RequestBroadcastSaveDTO requestBroadcastSaveDTO){

        UUID broadcastId = UUID.randomUUID();

        if (checkCreateBroadcast.exec(requestBroadcastSaveDTO))
            return null;

        String password = "";
        if (requestBroadcastSaveDTO.getBroadcastPassword() != null)
            password = requestBroadcastSaveDTO.getBroadcastPassword();

        return BroadcastDAO.builder()
                .broadcastId(broadcastId)
                .userId(requestBroadcastSaveDTO.getUserId())
                .title(requestBroadcastSaveDTO.getTitle())
                .totalTime("99:99:99")
                .videoUrl("/videos/" + broadcastId + ".webm")
                .broadcastPassword(password)
                .saveDate("99.99.99")
                .thumbnail("")
                .createAt(LocalDateTime.now())
                .uploadAt(LocalDateTime.now())
                .isEnded(false)
                .roomSetting(requestBroadcastSaveDTO.getRoomSetting())
                .build();
    }
}
