package com.example.live_tino.chat.service;

import com.example.live_tino.chat.bean.*;
import com.example.live_tino.chat.domain.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatService {

    GetChatMessagesBean getChatMessagesBean;
    DeleteChatMessageBean deleteChatMessageBean;
    DeleteChatRoomBean deleteChatRoomBean;
    DeleteChatUserBean deleteChatUserBean;

    @Autowired
    public ChatService(GetChatMessagesBean getChatMessagesBean, DeleteChatMessageBean deleteChatMessageBean, DeleteChatRoomBean deleteChatRoomBean, DeleteChatUserBean deleteChatUserBean){
        this.getChatMessagesBean = getChatMessagesBean;
        this.deleteChatMessageBean = deleteChatMessageBean;
        this.deleteChatRoomBean = deleteChatRoomBean;
        this.deleteChatUserBean = deleteChatUserBean;
    }

    // 채팅 전체 조회
    public List<ResponseChatMessagesGetDTO> getAllChat(UUID chatRoomId){
        return getChatMessagesBean.exec(chatRoomId);
    }

//    // 채팅방 생성
//    public UUID createChatRoom(RequestChatRoomCreateDTO requestChatRoomCreateDTO){
//        return saveChatRoomBean.exec(requestChatRoomCreateDTO);
//    }

    // 채팅 삭제
    public UUID deleteChat(RequestChatMessageDeleteDTO requestChatMessageDeleteDTO){
        return deleteChatMessageBean.exec(requestChatMessageDeleteDTO);
    }

    // 채팅방 삭제
    public UUID deleteChatRoom(RequestChatRoomDeleteDTO requestChatRoomDeleteDTO){
        return deleteChatRoomBean.exec(requestChatRoomDeleteDTO);
    }

    // 채팅 강퇴
    public UUID deleteChatUser(RequestChatUserDeleteDTO requestChatUserDeleteDTO){
        return deleteChatUserBean.exec(requestChatUserDeleteDTO);
    }
}
