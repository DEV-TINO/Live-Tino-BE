package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.repository.ChatMessageRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetChatMessageDAOBean {

    ChatMessageRepositoryJPA chatMessageRepositoryJPA;

    @Autowired
    public GetChatMessageDAOBean(ChatMessageRepositoryJPA chatMessageRepositoryJPA){
        this.chatMessageRepositoryJPA = chatMessageRepositoryJPA;
    }

    public ChatMessageDAO exec(UUID chatMessageId){
        return chatMessageRepositoryJPA.findById(chatMessageId).orElse(null);
    }
}
