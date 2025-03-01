package com.example.live_tino.chat.controller;

import com.example.live_tino.chat.domain.DTO.ChatMessage;
import com.example.live_tino.chat.domain.DTO.RequestChatMessageCreateDTO;
import com.example.live_tino.chat.domain.DTO.RequestMessageCreateDTO;
import com.example.live_tino.chat.rabbitMQ.RabbitMQConfig;
import com.example.live_tino.chat.service.MessageService;
import com.rabbitmq.client.Channel;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@Configuration
@AllArgsConstructor
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
        log.info("send Message: chatRoomId = {}, message = {}", message.getRoomId(), message);
        rabbitTemplate.convertAndSend("test.exchange", "chat.room." + message.getRoomId(), message);

        String destination = "/topic/message." + message.getRoomId();
        simpMessagingTemplate.convertAndSend(destination, message);
    }

    @RabbitListener(queues = "test.queue", ackMode = "MANUAL")
    public void receiveMessage(ChatMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            log.info("Received message: {}", message);

            // 메시지 정상 처리 후 수동 ACK
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("Message processing failed", e);

            // 문제가 발생하면 메시지를 다시 큐에 넣음
            channel.basicNack(tag, false, true);
        }
    }

}