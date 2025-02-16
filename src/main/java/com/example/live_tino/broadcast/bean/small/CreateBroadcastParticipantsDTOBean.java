package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastParticipantGetDTO;
import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateBroadcastParticipantsDTOBean {

    GetUserDAOBean getUserDAOBean;
    GetBroadcastDAOBean getBroadcastDAOBean;

    @Autowired
    public CreateBroadcastParticipantsDTOBean(GetUserDAOBean getUserDAOBean, GetBroadcastDAOBean getBroadcastDAOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.getBroadcastDAOBean = getBroadcastDAOBean;
    }

    public ResponseBroadcastParticipantGetDTO exec(BroadcastParticipantDAO broadcastParticipantDAO){
        return ResponseBroadcastParticipantGetDTO.builder()
                .userId(broadcastParticipantDAO.getUserId())
                .build();
    }

    public List<ResponseBroadcastParticipantGetDTO> exec(List<BroadcastParticipantDAO> broadcastParticipantDAOList, String loginId){

        List<ResponseBroadcastParticipantGetDTO> responseBroadcastParticipantGetDTOList = new ArrayList<>();

        UserDAO userDAO = getUserDAOBean.exec(loginId);
        BroadcastDAO broadcastDAO = getBroadcastDAOBean.exec2(userDAO.getUserId());

        // 날짜 별 동아리 타임 테이블 전체 리스트로 가져오기
        for (BroadcastParticipantDAO broadcastParticipantDAO : broadcastParticipantDAOList) {

            if (broadcastParticipantDAO.getBroadcastId().equals(broadcastDAO.getBroadcastId())) {
                ResponseBroadcastParticipantGetDTO responseBroadcastParticipantGetDTO = exec(broadcastParticipantDAO);

                responseBroadcastParticipantGetDTOList.add(responseBroadcastParticipantGetDTO);
            }
        }

        return responseBroadcastParticipantGetDTOList;
    }

}
