package com.example.live_tino.chat.bean.small;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.domain.DTO.ResponseChatMessagesGetDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CreateChatMessagesDTOBean {

    public ResponseChatMessagesGetDTO exec(ChatMessageDAO chatMessageDAO){
        return ResponseChatMessagesGetDTO.builder()
                .chatMessageId(chatMessageDAO.getChatMessageId())
                .chatRoomId(chatMessageDAO.getChatRoomId())
                .userId(chatMessageDAO.getUserId())
                .chatMessage(chatMessageDAO.getMessage())
                .build();
    }

    public List<ResponseChatMessagesGetDTO> exec(List<ChatMessageDAO> chatMessageDAOList, UUID chatRoomId){

        List<ResponseChatMessagesGetDTO> responseChatMessagesGetDTOList = new ArrayList<>();

        for(ChatMessageDAO chatMessageDAO : chatMessageDAOList){

            if (chatMessageDAO.getChatRoomId().equals(chatRoomId)){
                ResponseChatMessagesGetDTO responseChatMessagesGetDTO = exec(chatMessageDAO);

                responseChatMessagesGetDTOList.add(responseChatMessagesGetDTO);
            }
        }

        return responseChatMessagesGetDTOList;
    }

}
