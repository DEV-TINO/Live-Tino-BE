package com.example.live_tino.broadcast.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class SignalHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        log.debug("session added: {}", sessions);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트에서 WebRTC offer/answer, ICE candidate 등을 처리
        String payload = message.getPayload();
        log.debug("Received message: {}", payload);

        // 예시: type이 "offer" 또는 "answer"인 메시지 처리
        if (payload.contains("offer") || payload.contains("answer")) {
            // 연결하려는 상대에게 offer/answer 메시지 전달
            for (WebSocketSession s : sessions) {
                if (s.isOpen() && !s.getId().equals(session.getId())) {
                    log.debug("Sending WebRTC offer/answer to {}", s.getId());
                    s.sendMessage(message);
                }
            }
        }

        // 예시: ICE candidate 처리 (WebRTC의 ICE 후보 전송)
        else if (payload.contains("candidate")) {
            // 상대에게 ICE 후보를 전송
            for (WebSocketSession s : sessions) {
                if (s.isOpen() && !s.getId().equals(session.getId())) {
                    log.debug("Sending ICE candidate to {}", s.getId());
                    s.sendMessage(message);
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        log.debug("session removed: {}", session.getId());
    }
}
