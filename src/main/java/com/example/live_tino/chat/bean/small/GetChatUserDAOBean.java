package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatUserDAO;
import com.example.live_tino.chat.repository.ChatUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetChatUserDAOBean {

    ChatUserRepositoryJPA chatUserRepositoryJPA;

    @Autowired
    public GetChatUserDAOBean(ChatUserRepositoryJPA chatUserRepositoryJPA){
        this.chatUserRepositoryJPA = chatUserRepositoryJPA;
    }

    public ChatUserDAO exec(UUID chatRoomId, UUID userId){
        return chatUserRepositoryJPA.findByChatRoomIdAndUserId(chatRoomId, userId);
    }
}
