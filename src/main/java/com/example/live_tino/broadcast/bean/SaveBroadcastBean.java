package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.CheckCreateBroadcast;
import com.example.live_tino.broadcast.bean.small.CreateBroadcastDAOBean;
import com.example.live_tino.broadcast.bean.small.SaveBroadcastDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastAndChatSaveDTO;
import com.example.live_tino.chat.bean.small.CreateChatRoomDAOBean;
import com.example.live_tino.chat.bean.small.SaveChatRoomDAOBean;
import com.example.live_tino.chat.domain.ChatRoomDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class SaveBroadcastBean {

    CreateBroadcastDAOBean createBroadcastDAOBean;
    SaveBroadcastDAOBean saveBroadcastDAOBean;
    CheckCreateBroadcast checkCreateBroadcast;
    CreateChatRoomDAOBean createChatRoomDAOBean;
    SaveChatRoomDAOBean saveChatRoomDAOBean;

    @Autowired
    public SaveBroadcastBean(CreateBroadcastDAOBean createBroadcastDAOBean, SaveBroadcastDAOBean saveBroadcastDAOBean, CheckCreateBroadcast checkCreateBroadcast, CreateChatRoomDAOBean createChatRoomDAOBean, SaveChatRoomDAOBean saveChatRoomDAOBean){
        this.createBroadcastDAOBean = createBroadcastDAOBean;
        this.saveBroadcastDAOBean = saveBroadcastDAOBean;
        this.checkCreateBroadcast = checkCreateBroadcast;
        this.createChatRoomDAOBean = createChatRoomDAOBean;
        this.saveChatRoomDAOBean = saveChatRoomDAOBean;
    }

    public UUID exec(RequestBroadcastAndChatSaveDTO requestBroadcastAndChatSaveDTO){

        BroadcastDAO broadcastDAO = createBroadcastDAOBean.exec(requestBroadcastAndChatSaveDTO);
        if (broadcastDAO == null) return null;

        saveBroadcastDAOBean.exec(broadcastDAO);

        ChatRoomDAO chatRoomDAO = createChatRoomDAOBean.exec(requestBroadcastAndChatSaveDTO, broadcastDAO);
        if (chatRoomDAO == null) return null;

        saveChatRoomDAOBean.exec(chatRoomDAO);

        log.info("broadcastId : {}", broadcastDAO.getBroadcastId());
        log.info("chatRoomId : {}", chatRoomDAO.getChatRoomId());

        return broadcastDAO.getBroadcastId();
    }

}
