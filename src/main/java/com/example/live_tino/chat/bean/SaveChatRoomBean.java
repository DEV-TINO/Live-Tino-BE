//package com.example.live_tino.chat.bean;
//
//import com.example.live_tino.chat.bean.small.CreateChatRoomDAOBean;
//import com.example.live_tino.chat.bean.small.SaveChatRoomDAOBean;
//import com.example.live_tino.chat.domain.ChatRoomDAO;
//import com.example.live_tino.chat.domain.DTO.RequestChatRoomCreateDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@Slf4j
//public class SaveChatRoomBean {
//
//    CreateChatRoomDAOBean createChatRoomDAOBean;
//    SaveChatRoomDAOBean saveChatRoomDAOBean;
//
//    @Autowired
//    public SaveChatRoomBean(CreateChatRoomDAOBean createChatRoomDAOBean, SaveChatRoomDAOBean saveChatRoomDAOBean){
//        this.createChatRoomDAOBean = createChatRoomDAOBean;
//        this.saveChatRoomDAOBean = saveChatRoomDAOBean;
//    }
//
//    public UUID exec(RequestChatRoomCreateDTO requestChatRoomCreateDTO){
//        ChatRoomDAO chatRoomDAO = createChatRoomDAOBean.exec(requestChatRoomCreateDTO);
//        if (chatRoomDAO == null) return null;
//
//        saveChatRoomDAOBean.exec(chatRoomDAO);
//
//        log.info("chatRoomId : {}", chatRoomDAO.getChatRoomId());
//
//        return chatRoomDAO.getChatRoomId();
//    }
//}
