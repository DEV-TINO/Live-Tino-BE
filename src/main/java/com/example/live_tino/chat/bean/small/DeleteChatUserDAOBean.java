package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatUserDAO;
import com.example.live_tino.chat.repository.ChatUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteChatUserDAOBean {

    ChatUserRepositoryJPA chatUserRepositoryJPA;

    @Autowired
    public DeleteChatUserDAOBean(ChatUserRepositoryJPA chatUserRepositoryJPA){
        this.chatUserRepositoryJPA = chatUserRepositoryJPA;
    }

    public void exec(ChatUserDAO chatUserDAO){
        chatUserRepositoryJPA.delete(chatUserDAO);
    }
}
