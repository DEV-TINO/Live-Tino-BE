package com.example.live_tino.chat.service;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.domain.DTO.ChatMessage;
import com.example.live_tino.chat.domain.DTO.RequestChatMessageCreateDTO;
import com.example.live_tino.chat.domain.DTO.RequestMessageCreateDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // @Autowired
    public MessageService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enterUser(UUID chatRoomId, RequestMessageCreateDTO requestMessageCreateDTO){
        rabbitTemplate.convertAndSend(exchangeName, routingKey + chatRoomId, requestMessageCreateDTO);
    }

    public void sendMessage(ChatMessage chatMessage){
        rabbitTemplate.convertAndSend(exchangeName, "chat.room." + chatMessage.getRoomId(), chatMessage);
    }
}
