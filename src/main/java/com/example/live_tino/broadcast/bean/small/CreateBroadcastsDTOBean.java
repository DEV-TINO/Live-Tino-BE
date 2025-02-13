package com.example.live_tino.broadcast.bean.small;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.ResponseBroadcastsGetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateBroadcastsDTOBean {

    public ResponseBroadcastsGetDTO exec(BroadcastDAO broadcastDAO){
        return ResponseBroadcastsGetDTO.builder()
                .broadcastId(broadcastDAO.getBroadcastId())
                .title(broadcastDAO.getTitle())
                .saveDate(broadcastDAO.getSaveDate())
                .totalTime(broadcastDAO.getTotalTime())
                .thumbnail(broadcastDAO.getThumbnail())
                .build();
    }

    public Page<ResponseBroadcastsGetDTO> exec(Page<BroadcastDAO> broadcastDAOPage, UUID userId, Pageable pageable) {

        List<ResponseBroadcastsGetDTO> responseBroadcastsGetDTOList  = new ArrayList<>();

        for (BroadcastDAO broadcastDAO : broadcastDAOPage.getContent()) {

            if (broadcastDAO.getUserId().equals(userId)) {
                ResponseBroadcastsGetDTO responseBroadcastsGetDTO = exec(broadcastDAO);

                responseBroadcastsGetDTOList.add(responseBroadcastsGetDTO);
            }
        }

        return new PageImpl<>(responseBroadcastsGetDTOList, pageable, broadcastDAOPage.getTotalElements());
    }
}
