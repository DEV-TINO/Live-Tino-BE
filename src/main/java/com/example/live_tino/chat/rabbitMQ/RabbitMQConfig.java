package com.example.live_tino.chat.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Configuration
@EnableRabbit
@Slf4j
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitmqPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    // 큐 로그 찍어봐서 일단 로그 확인해보기
    // 권한
    @Bean
    public Queue chatQueue(){
        return new Queue("test.queue", true);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("test.exchange");
    }

    @Bean
    public Binding binding(Queue chatQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(chatQueue)
                .to(exchange)
                .with("chat.room.#");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate){
        return new RabbitMessagingTemplate(rabbitTemplate);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
//        String urlEncodedPassword = URLEncoder.encode("1234", "UTF-8");

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("livetino-rabbit");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("1234");
        // connectionFactory.setVirtualHost("/");
//        connectionFactory.setUri("amqp://test:"+ urlEncodedPassword+ "@localhost:5672/vhost");

        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    ApplicationRunner runner(CachingConnectionFactory ccf) {
        return args -> {
            log.info("userName : {}", ccf.getRabbitConnectionFactory().getUsername());
            log.info("password : {}", ccf.getRabbitConnectionFactory().getPassword());
        };
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}

