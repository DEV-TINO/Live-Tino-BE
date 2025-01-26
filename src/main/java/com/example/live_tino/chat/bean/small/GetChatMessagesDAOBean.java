package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.repository.ChatMessageRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetChatMessagesDAOBean {

    ChatMessageRepositoryJPA chatMessageRepositoryJPA;

    @Autowired
    public GetChatMessagesDAOBean(ChatMessageRepositoryJPA chatMessageRepositoryJPA){
        this.chatMessageRepositoryJPA = chatMessageRepositoryJPA;
    }

    public List<ChatMessageDAO> exec(){
        return chatMessageRepositoryJPA.findAllByOrderByCreateAtAsc();
    }
}
