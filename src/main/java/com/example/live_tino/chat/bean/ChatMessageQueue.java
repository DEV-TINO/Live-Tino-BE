package com.example.live_tino.chat.bean;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.domain.DTO.ChatMessage;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ChatMessageQueue {

    private final Queue<ChatMessageDAO> messagesQueue = new ConcurrentLinkedQueue<>();

    // 메시지 추가
    public void addMessage(ChatMessageDAO message){
        messagesQueue.offer(message);
    }

    // 메시지 가져오기 (큐에서 제거됨)
    public ChatMessageDAO pollMessage(){
        return messagesQueue.poll();
    }

    // 현재 큐의 크기
    public int getQueueSize(){
        return messagesQueue.size();
    }
}
