package com.example.live_tino.broadcast.service;

import com.example.live_tino.broadcast.bean.*;
import com.example.live_tino.broadcast.domain.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BroadcastService {

    GetBroadcastsBean getBroadcastsBean;
    GetParticipantsBean getParticipantsBean;
    SaveBroadcastBean saveBroadcastBean;
    SaveBroadcastParticipantBean saveBroadcastParticipantBean;
    UpdateQuitBroadcastBean updateQuitBroadcastBean;
    UpdateBroadcastBean updateBroadcastBean;
    DeleteBroadcastBean deleteBroadcastBean;
    DeleteBroadcastParticipantBean deleteBroadcastParticipantBean;

    @Autowired
    public BroadcastService(GetBroadcastsBean getBroadcastsBean, GetParticipantsBean getParticipantsBean, SaveBroadcastBean saveBroadcastBean, SaveBroadcastParticipantBean saveBroadcastParticipantBean, UpdateQuitBroadcastBean updateQuitBroadcastBean, UpdateBroadcastBean updateBroadcastBean, DeleteBroadcastBean deleteBroadcastBean, DeleteBroadcastParticipantBean deleteBroadcastParticipantBean){
        this.getBroadcastsBean = getBroadcastsBean;
        this.getParticipantsBean = getParticipantsBean;
        this.saveBroadcastBean = saveBroadcastBean;
        this.saveBroadcastParticipantBean = saveBroadcastParticipantBean;
        this.updateQuitBroadcastBean = updateQuitBroadcastBean;
        this.updateBroadcastBean = updateBroadcastBean;
        this.deleteBroadcastBean = deleteBroadcastBean;
        this.deleteBroadcastParticipantBean = deleteBroadcastParticipantBean;
    }

    // 전체 방송 조회
    public Page<ResponseBroadcastsGetDTO> getAllBroadcast(UUID userId, Pageable pageable){
        return getBroadcastsBean.exec(userId, pageable);
    }

    // 특정 방송 조회



    // 전체 방송 참여 인원 조회
    public List<ResponseBroadcastParticipantGetDTO> getAllParticipant(String loginId){
        return getParticipantsBean.exec(loginId);
    }

    // 방송 생성
    public UUID createBroadcastAndChat(RequestBroadcastAndChatSaveDTO requestBroadcastAndChatSaveDTO){
        return saveBroadcastBean.exec(requestBroadcastAndChatSaveDTO);
    }

    // 방송 참여
    public ResponseBroadcastJoinGetDTO createBroadcastParticipant(RequestBroadcastParticipantSaveDTO requestBroadcastParticipantSaveDTO){
        return saveBroadcastParticipantBean.exec(requestBroadcastParticipantSaveDTO);
    }

    // canvas 저장


    // 방송 종료
    public UUID updateQuitBroadcast(RequestBroadcastQuitUpdateDTO requestBroadcastQuitUpdateDTO){
        return updateQuitBroadcastBean.exec(requestBroadcastQuitUpdateDTO);
    }


    // 방송 수정
    public UUID updateBroadcast(RequestBroadcastUpdateDTO requestBroadcastUpdateDTO){
        return updateBroadcastBean.exec(requestBroadcastUpdateDTO);
    }


    // 방송 삭제
    public UUID deleteBroadcast(RequestBroadcastDeleteDTO requestBroadcastDeleteDTO){
        return deleteBroadcastBean.exec(requestBroadcastDeleteDTO);
    }


    // 사용자 강퇴(삭제)
    public UUID deleteParticipantBroadcast(RequestBroadcastParticipantDeleteDTO requestBroadcastParticipantDeleteDTO){
        return deleteBroadcastParticipantBean.exec(requestBroadcastParticipantDeleteDTO);
    }
}
