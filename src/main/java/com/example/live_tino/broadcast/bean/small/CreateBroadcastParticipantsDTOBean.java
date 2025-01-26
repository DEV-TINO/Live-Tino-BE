package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastParticipantGetDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateBroadcastParticipantsDTOBean {

    public ResponseBroadcastParticipantGetDTO exec(BroadcastParticipantDAO broadcastParticipantDAO){
        return ResponseBroadcastParticipantGetDTO.builder()
                .userId(broadcastParticipantDAO.getUserId())
                .nickname(broadcastParticipantDAO.getNickname())
                .build();
    }

    public List<ResponseBroadcastParticipantGetDTO> exec(List<BroadcastParticipantDAO> broadcastParticipantDAOList, UUID broadcastId){

        List<ResponseBroadcastParticipantGetDTO> responseBroadcastParticipantGetDTOList = new ArrayList<>();

        // 날짜 별 동아리 타임 테이블 전체 리스트로 가져오기
        for (BroadcastParticipantDAO broadcastParticipantDAO : broadcastParticipantDAOList) {

            if (broadcastParticipantDAO.getBroadcastId().equals(broadcastId)) {
                ResponseBroadcastParticipantGetDTO responseBroadcastParticipantGetDTO = exec(broadcastParticipantDAO);

                responseBroadcastParticipantGetDTOList.add(responseBroadcastParticipantGetDTO);
            }
        }

        return responseBroadcastParticipantGetDTOList;
    }

}
