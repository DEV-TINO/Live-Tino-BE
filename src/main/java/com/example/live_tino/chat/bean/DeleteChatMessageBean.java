package com.example.live_tino.chat.bean;

import com.example.live_tino.chat.bean.small.DeleteChatMessageDAOBean;
import com.example.live_tino.chat.bean.small.GetChatMessageDAOBean;
import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.domain.DTO.RequestChatMessageDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteChatMessageBean {

    GetChatMessageDAOBean getChatMessageDAOBean;
    DeleteChatMessageDAOBean deleteChatMessageDAOBean;

    @Autowired
    public DeleteChatMessageBean(GetChatMessageDAOBean getChatMessageDAOBean, DeleteChatMessageDAOBean deleteChatMessageDAOBean){
        this.getChatMessageDAOBean = getChatMessageDAOBean;
        this.deleteChatMessageDAOBean = deleteChatMessageDAOBean;
    }

    public UUID exec(RequestChatMessageDeleteDTO requestChatMessageDeleteDTO){
        UUID chatMessageId = requestChatMessageDeleteDTO.getChatMessageId();

        ChatMessageDAO chatMessageDAO = getChatMessageDAOBean.exec(chatMessageId);
        if (chatMessageDAO == null) return null;

        deleteChatMessageDAOBean.exec(chatMessageDAO);

        return chatMessageId;
    }
}
