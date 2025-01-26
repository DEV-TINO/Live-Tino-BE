package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatRoomDAO;
import com.example.live_tino.chat.repository.ChatRoomRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveChatRoomDAOBean {

    ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public SaveChatRoomDAOBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA){
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    public void exec(ChatRoomDAO chatRoomDAO){
        chatRoomRepositoryJPA.save(chatRoomDAO);
    }
}
