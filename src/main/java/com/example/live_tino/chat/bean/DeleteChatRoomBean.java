package com.example.live_tino.chat.bean;

import com.example.live_tino.chat.bean.small.DeleteChatRoomDAOBean;
import com.example.live_tino.chat.bean.small.GetChatRoomDAOBean;
import com.example.live_tino.chat.domain.ChatRoomDAO;
import com.example.live_tino.chat.domain.DTO.RequestChatRoomDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteChatRoomBean {

    GetChatRoomDAOBean getChatRoomDAOBean;
    DeleteChatRoomDAOBean deleteChatRoomDAOBean;

    @Autowired
    public DeleteChatRoomBean(GetChatRoomDAOBean getChatRoomDAOBean, DeleteChatRoomDAOBean deleteChatRoomDAOBean){
        this.getChatRoomDAOBean = getChatRoomDAOBean;
        this.deleteChatRoomDAOBean = deleteChatRoomDAOBean;
    }

    public UUID exec(RequestChatRoomDeleteDTO requestChatRoomDeleteDTO){
        UUID chatMessageId = requestChatRoomDeleteDTO.getChatRoomId();

        ChatRoomDAO chatRoomDAO = getChatRoomDAOBean.exec(chatMessageId);
        if(chatRoomDAO == null) return null;

        deleteChatRoomDAOBean.exec(chatRoomDAO);

        return chatMessageId;
    }
}
