package com.example.live_tino.chat.controller;

import com.example.live_tino.chat.bean.ChatMessageQueue;
import com.example.live_tino.chat.domain.DTO.ChatMessage;
import com.example.live_tino.chat.domain.DTO.RequestChatMessageCreateDTO;
import com.example.live_tino.chat.domain.DTO.RequestMessageCreateDTO;
import com.example.live_tino.chat.rabbitMQ.RabbitMQConfig;
import com.example.live_tino.chat.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@Configuration
@Slf4j
public class MessageController {

    MessageService messageService;
    RabbitMQConfig rabbitMQConfig;
    SimpMessagingTemplate simpMessagingTemplate;

    final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Autowired
    public MessageController(MessageService messageService, RabbitMQConfig rabbitMQConfig, RabbitTemplate rabbitTemplate, SimpMessagingTemplate simpMessagingTemplate){
        this.messageService = messageService;
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitTemplate = rabbitTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("chat.enter.{chatRoomId}")
    public void enterUser(@Payload RequestChatMessageCreateDTO requestChatMessageCreateDTO){
        requestChatMessageCreateDTO.setContent(requestChatMessageCreateDTO.getSender() + "님 입장!");
        rabbitTemplate.convertAndSend(exchangeName, "room." + requestChatMessageCreateDTO.getChatRoomId(), requestChatMessageCreateDTO);
    }

    @MessageMapping("chat.message")
    public void sendMessage(ChatMessage message){
        String routingKey = "chat.room." + message.getRoomId();
        log.info("Sending message from {} to Exchange: {}, Routing Key: {}",message.getSender(), exchangeName, routingKey);
        rabbitTemplate.convertAndSend("test.exchange", routingKey, message);

        String destination = "/topic/message." + message.getRoomId();
        simpMessagingTemplate.convertAndSend(destination, message);
    }

    @RabbitListener(queues = "test.queue")
    public void receive(Message message) {
        try {
            String jsonString = new String(message.getBody(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            ChatMessage chatMessage = objectMapper.readValue(jsonString, ChatMessage.class);
            log.info("Received Message from {}: {}",chatMessage.getSender(), chatMessage);
        } catch (Exception e) {
            log.error("Message conversion error", e);
        }
    }
}
