package com.example.live_tino.chat.controller;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.domain.DTO.*;
import com.example.live_tino.chat.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    ChatService chatService;

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessageSendingOperations messagingTemplate){
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    // 채팅 전체 조회
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<Map<String, Object>> getAllChat(@PathVariable("chatRoomId")UUID chatRoomId) {

        List<ResponseChatMessagesGetDTO> responseChatMessagesGetDTOList = chatService.getAllChat(chatRoomId);

        boolean success = (responseChatMessagesGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "채팅 전체 조회 성공" : "채팅 전체 조회 실패");
        requestMap.put("chatMessageList", responseChatMessagesGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

//    // 채팅방 생성
//    @PostMapping("/create")
//    public ResponseEntity<Map<String, Object>> createChatRoom(@RequestBody RequestChatRoomCreateDTO requestChatRoomCreateDTO){
//
//        UUID chatRoomId = chatService.createChatRoom(requestChatRoomCreateDTO);
//
//        log.info("chatRoomId : {}", chatRoomId);
//
//        boolean success = (chatRoomId == null) ? false : true;
//
//        Map<String, Object> requestMap = new HashMap<>();
//        requestMap.put("success", success);
//        requestMap.put("message", success ? "채팅방 생성 성공" : "채팅방 생성 실패");
//        requestMap.put("chatRoomId", chatRoomId);
//
//        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
//    }



    // 채팅 삭제
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteChat(@RequestBody RequestChatMessageDeleteDTO requestChatMessageDeleteDTO){

        UUID chatMessageId = chatService.deleteChat(requestChatMessageDeleteDTO);

        boolean success = (chatMessageId == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "채팅 삭제 성공" : "채팅 삭제 실패");
        requestMap.put("chatMessageId", chatMessageId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 채팅방 삭제
    @DeleteMapping("/room")
    public ResponseEntity<Map<String, Object>> deleteChatRoom(@RequestBody RequestChatRoomDeleteDTO requestChatRoomDeleteDTO){

        UUID chatRoomId = chatService.deleteChatRoom(requestChatRoomDeleteDTO);

        boolean success = (chatRoomId == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "채팅방 삭제 성공" : "채팅방 삭제 실패");
        requestMap.put("chatRoomId", chatRoomId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 채팅 강퇴
    @DeleteMapping("/user/exit")
    public ResponseEntity<Map<String, Object>> deleteChatUser(@RequestBody RequestChatUserDeleteDTO requestChatUserDeleteDTO){

        UUID userId = chatService.deleteChatUser(requestChatUserDeleteDTO);

        boolean success = (userId == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "유저 강퇴 성공" : "유저 강퇴 실패");
        requestMap.put("userId", userId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 메세지 전송
    @MessageMapping("/message")
    public void message(ChatMessageDAO chatMessageDAO){
        messagingTemplate.convertAndSend("/topic" + chatMessageDAO.getChatRoomId(), chatMessageDAO);
    }
}
