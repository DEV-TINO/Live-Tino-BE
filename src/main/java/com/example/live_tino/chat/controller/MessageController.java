package com.example.live_tino.chat.controller;

import com.example.live_tino.chat.domain.DTO.ChatMessage;
import com.example.live_tino.chat.domain.DTO.RequestChatMessageCreateDTO;
import com.example.live_tino.chat.domain.DTO.RequestMessageCreateDTO;
import com.example.live_tino.chat.rabbitMQ.RabbitMQConfig;
import com.example.live_tino.chat.service.MessageService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
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

import java.time.LocalDateTime;
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
    SimpMessagingTemplate simpMessagingTemplate;
    // private SimpMessagingTemplate simpMessagingTemplate;

    final RabbitTemplate rabbitTemplate;
    //RabbitTemplate rtemp = rab;

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

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/messages")  // 해당 경로로 메시지 전송
//    public RequestMessageCreateDTO sendMessage(RequestMessageCreateDTO requestMessageCreateDTO) {
//        return requestMessageCreateDTO;  // 받은 메시지를 그대로 반환하여 "/topic/messages"로 발송
//    }

//    public void sendMessageToClient(String message) {
//        simpMessagingTemplate.convertAndSend("/topic/messages", message);
//    }

//    @MessageMapping("chat.enter.{chatRoomId}")
//    public void enterUser(@Payload RequestChatMessageCreateDTO requestChatMessageCreateDTO, @DestinationVariable UUID chatRoomId){
//        chat.setCreateAt(LocalDateTime.now());
//        requestChatMessageCreateDTO.setNickName(requestChatMessageCreateDTO.getNickName() + "님 입장!");
//        chat.setChatMessage(chat.getSender() + "님 입장!");
//        rabbitTemplate.convertAndSend(exchangeName, "room." + chatRoomId, chat);
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void sender1(){
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String time = localDateTime.toString();
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setContent(time);
//        chatMessage.setRoomId("1");
//        System.out.println("-> " + chatMessage);
//        rabbitTemplate.convertAndSend("test.exchange", "chat.room.#", chatMessage.toString());
//    }

    @MessageMapping("chat.message")
    public void sendMessage(ChatMessage message){
        log.info("send Message: chatRoomId = {}, message = {}", message.getRoomId(), message);
        rabbitTemplate.convertAndSend("test.exchange", "chat.room.#", message);
//        messageService.sendMessage(message);

        String destination = "/topic/message." + message.getRoomId();
        simpMessagingTemplate.convertAndSend(destination, message);
    }


    @RabbitListener
    public void receive(ChatMessage chatMessage){
        rabbitMQConfig.binding(rabbitMQConfig.chatQueue(), rabbitMQConfig.exchange());
        rabbitMQConfig.simpleRabbitListenerContainerFactory(rabbitMQConfig.connectionFactory());
        System.out.println("<- " + chatMessage.toString());
        rabbitTemplate.convertAndSend("test.exchange", "chat.room.#", chatMessage);
    }


//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(name="time", type = ExchangeTypes.TOPIC),
//            value = @Queue(name = "time-second"),
//            key = "time-first"
//    ))
//    public void receiver(String msg){
//        System.out.println("receiver : " + msg);
//    }





//    @Scheduled(fixedDelay = 5000)
//    public void sender(){
//
//        LocalDateTime nowDateTime = LocalDateTime.now();
//        String time = nowDateTime.toString();
//        String exchangeName = "amq.direct";
//        String routingKey = "chat.room";
//
//
//
//        System.out.println("time : " + time);
//        // rabbitMQConfig.rabbitTemplate(rabbitMQConfig.connectionFactory());
//
//        // rabbitTemplate.convertAndSend("time", "time-first", time);
//
//        // this.rtemp.convertAndSend(exchangeName, routingKey, "test");
//        System.out.println(this.rabbitTemplate.getExchange());
//        System.out.println(this.rabbitTemplate.getRoutingKey());
//        System.out.println(this.rabbitTemplate.getClass());
//        System.out.println(this.rabbitTemplate.getConnectionFactory().getHost());
//        System.out.println(this.rabbitTemplate.getConnectionFactory().getUsername());
//        System.out.println(this.rabbitTemplate.getConnectionFactory().getPort());
//        System.out.println(this.rabbitTemplate.getConnectionFactory().getVirtualHost());
//
//        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, "test");
//
//
//        log.info("rabbitTemplate : {}", this.rabbitTemplate);
//    }

//    @PostConstruct
//    public void init() {
//        log.info("RabbitMQ 연결 성공");
//    }


//    @
//    public void test(){
//
//    }

//    @RabbitListener(queues = "#{${rabbitmq.queue.name}}")
//    public void receive(RequestMessageCreateDTO requestMessageCreateDTO) {
//        System.out.println("receiver : " + requestMessageCreateDTO.getChatMessage());
//    }

}
