//package com.example.live_tino.chat.webSocket;
//
//import com.example.live_tino.user.jwt.JwtFilter;
//import io.netty.channel.ChannelInboundHandler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//
//import javax.security.auth.login.AccountException;
//
//@Configuration
//@RequiredArgsConstructor
//public class StompHandler implements ChannelInboundHandler {
//
//    private final JwtFilter jwtFilter;
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//
//        if(accessor.getCommand() == StompCommand.CONNECT) {
//            validateToken(accessor);
//        }
//
//        return message;
//    }
//
////    private void validateToken(StompHeaderAccessor accessor) {
////        String accessToken = jwtFilter.resolveToken(accessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION));
////
////        if (accessToken == null)
////            throw new AccountException(StatusCode.FILTER_ACCESS_DENIED);
////
////        jwtFilter.validate(accessToken);
////    }
