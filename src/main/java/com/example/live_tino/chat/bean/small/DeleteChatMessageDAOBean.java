package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.repository.ChatMessageRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteChatMessageDAOBean {

    ChatMessageRepositoryJPA chatMessageRepositoryJPA;

    @Autowired
    public DeleteChatMessageDAOBean(ChatMessageRepositoryJPA chatMessageRepositoryJPA){
        this.chatMessageRepositoryJPA = chatMessageRepositoryJPA;
    }

    public void exec(ChatMessageDAO chatMessageDAO){
        chatMessageRepositoryJPA.delete(chatMessageDAO);
    }
}
