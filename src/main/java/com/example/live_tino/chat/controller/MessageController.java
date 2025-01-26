package com.example.live_tino.chat.controller;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import com.example.live_tino.chat.domain.DTO.RequestChatMessageCreateDTO;
import com.example.live_tino.chat.domain.DTO.RequestMessageCreateDTO;
import com.example.live_tino.chat.domain.DTO.ResponseChatMessagesGetDTO;
import com.example.live_tino.chat.rabbitMQ.RabbitMQConfig;
import com.example.live_tino.chat.service.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
//@RequestMapping("/message")
@Configuration
@AllArgsConstructor
@Slf4j
public class MessageController {

//    private static final String queueName = "rabbit.queue.name";
    MessageService messageService;
    RabbitMQConfig rabbitMQConfig;
    // private SimpMessagingTemplate simpMessagingTemplate;

    final RabbitTemplate rabbitTemplate;
    //RabbitTemplate rtemp = rab;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Autowired
    public MessageController(MessageService messageService, RabbitMQConfig rabbitMQConfig, RabbitTemplate rabbitTemplate){
        this.messageService = messageService;
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitTemplate = rabbitTemplate;
        // this.rtemp = this.rabbitMQConfig.rabbitTemplate(this.rabbitMQConfig.connectionFactory());
        // this.simpMessagingTemplate = simpMessagingTemplate;
    }

//    @MessageMapping("chat.enter.{chatRoomId}")
//    public void enterUser(@DestinationVariable UUID chatRoomId, RequestMessageCreateDTO requestMessageCreateDTO){
//        requestMessageCreateDTO.setCreateAt(LocalDateTime.now());
//        requestMessageCreateDTO.setChatMessage(requestMessageCreateDTO.getChatMessage() + "입장");
//        messageService.enterUser(chatRoomId, requestMessageCreateDTO);
//    }

//    @MessageMapping("chat.message.{chatRoomId}")
//    public void sendMessage(@DestinationVariable UUID chatRoomId, RequestMessageCreateDTO requestMessageCreateDTO){
//        requestMessageCreateDTO.setCreateAt(LocalDateTime.now());
//        requestMessageCreateDTO.setChatMessage(requestMessageCreateDTO.getChatMessage());
//        messageService.sendMessage(chatRoomId, requestMessageCreateDTO);
//    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")  // 해당 경로로 메시지 전송
    public RequestMessageCreateDTO sendMessage(RequestMessageCreateDTO requestMessageCreateDTO) {
        return requestMessageCreateDTO;  // 받은 메시지를 그대로 반환하여 "/topic/messages"로 발송
    }

//    public void sendMessageToClient(String message) {
//        simpMessagingTemplate.convertAndSend("/topic/messages", message);
//    }


    @Scheduled(fixedDelay = 5000)
    public void sender(){

        LocalDateTime nowDateTime = LocalDateTime.now();
        String time = nowDateTime.toString();
        String exchangeName = "amq.direct";
        String routingKey = "chat.room";



        System.out.println("time : " + time);
        // rabbitMQConfig.rabbitTemplate(rabbitMQConfig.connectionFactory());

        // rabbitTemplate.convertAndSend("time", "time-first", time);

        // this.rtemp.convertAndSend(exchangeName, routingKey, "test");
        System.out.println(this.rabbitTemplate.getExchange());
        System.out.println(this.rabbitTemplate.getRoutingKey());
        System.out.println(this.rabbitTemplate.getClass());
        System.out.println(this.rabbitTemplate.getConnectionFactory().getHost());
        System.out.println(this.rabbitTemplate.getConnectionFactory().getUsername());
        System.out.println(this.rabbitTemplate.getConnectionFactory().getPort());
        System.out.println(this.rabbitTemplate.getConnectionFactory().getVirtualHost());

        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, "test");


        log.info("rabbitTemplate : {}", this.rabbitTemplate);
    }

    @PostConstruct
    public void init() {
        log.info("RabbitMQ 연결 성공");
    }


//    @
//    public void test(){
//
//    }

//    @RabbitListener(queues = "#{${rabbitmq.queue.name}}")
//    public void receive(RequestMessageCreateDTO requestMessageCreateDTO) {
//        System.out.println("receiver : " + requestMessageCreateDTO.getChatMessage());
//    }

}
