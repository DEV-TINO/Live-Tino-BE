package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatRoomDAO;
import com.example.live_tino.chat.repository.ChatRoomRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetChatRoomDAOBean {

    ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public GetChatRoomDAOBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA){
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    public ChatRoomDAO exec(UUID chatRoomId){
        return chatRoomRepositoryJPA.findById(chatRoomId).orElse(null);
    }
}
