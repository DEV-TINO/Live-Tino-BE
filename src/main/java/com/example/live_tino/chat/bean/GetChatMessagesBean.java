package com.example.live_tino.chat.bean;

import com.example.live_tino.chat.bean.small.CreateChatMessagesDTOBean;
import com.example.live_tino.chat.bean.small.GetChatMessagesDAOBean;
import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.domain.DTO.ResponseChatMessagesGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetChatMessagesBean {

    GetChatMessagesDAOBean getChatMessagesDAOBean;
    CreateChatMessagesDTOBean createChatMessagesDTOBean;

    @Autowired
    public GetChatMessagesBean(GetChatMessagesDAOBean getChatMessagesDAOBean, CreateChatMessagesDTOBean createChatMessagesDTOBean){
        this.getChatMessagesDAOBean = getChatMessagesDAOBean;
        this.createChatMessagesDTOBean = createChatMessagesDTOBean;
    }

    public List<ResponseChatMessagesGetDTO> exec(UUID chatRoomId){

        List<ChatMessageDAO> chatMessageDAOList = getChatMessagesDAOBean.exec();
        if (chatMessageDAOList == null) return null;

        return createChatMessagesDTOBean.exec(chatMessageDAOList, chatRoomId);
    }
}
