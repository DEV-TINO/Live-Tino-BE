package com.example.live_tino.chat.bean;

import com.example.live_tino.chat.bean.small.DeleteChatUserDAOBean;
import com.example.live_tino.chat.bean.small.GetChatUserDAOBean;
import com.example.live_tino.chat.domain.ChatUserDAO;
import com.example.live_tino.chat.domain.DTO.RequestChatUserDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteChatUserBean {

    GetChatUserDAOBean getChatUserDAOBean;
    DeleteChatUserDAOBean deleteChatUserDAOBean;

    @Autowired
    public DeleteChatUserBean(GetChatUserDAOBean getChatUserDAOBean, DeleteChatUserDAOBean deleteChatUserDAOBean){
        this.getChatUserDAOBean = getChatUserDAOBean;
        this.deleteChatUserDAOBean = deleteChatUserDAOBean;
    }

    public UUID exec(RequestChatUserDeleteDTO requestChatUserDeleteDTO){
        UUID chatRoomId = requestChatUserDeleteDTO.getChatRoomId();
        UUID userId = requestChatUserDeleteDTO.getUserId();

        ChatUserDAO chatUserDAO = getChatUserDAOBean.exec(chatRoomId, userId);
        if(chatUserDAO == null) return null;

        deleteChatUserDAOBean.exec(chatUserDAO);

        return userId;
    }
}
