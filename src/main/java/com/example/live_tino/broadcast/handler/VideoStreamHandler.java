package com.example.live_tino.broadcast.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
public class VideoStreamHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        log.debug("session added: {}", sessions);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.debug("message: {}", message);
        // Broadcast the received message to all connected clients
        for (WebSocketSession s : sessions) {
            if (s.isOpen() && !s.getId().equals(session.getId())) {
                log.debug("sendMessage to {}", s.getId());
                s.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

//    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//    ConcurrentHashMap<String, FileOutputStream> videoStreams = new ConcurrentHashMap<>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
//        String broadcastId = extractBroadcastId(session);
//        if (broadcastId == null){
//            log.error("방송 ID 없음 연결 종료");
//            session.close(CloseStatus.BAD_DATA);
//            return;
//        }
//
//        sessions.add(session);
//
//        String filePath = "/videos/" + broadcastId + ".webm";
//        FileOutputStream videoOutputStream = new FileOutputStream(filePath);
//        videoStreams.put(broadcastId, videoOutputStream);
//        log.info("방송 저장 시작 : {}", filePath);
//    }
//
//    @Override
//    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException{
//        String broadcastId = extractBroadcastId(session);
//        if (broadcastId == null || !videoStreams.containsKey(broadcastId)){
//            log.error("broadcastId를 찾을 수 없음.");
//            return;
//        }
//
//        byte[] payload = message.getPayload().array();
//        videoStreams.get(broadcastId).write(payload);
//        videoStreams.get(broadcastId).flush();
//        log.info("broadcastId({}) -> chunk 데이터 저장 중..", broadcastId);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
//        // 클라이언트에서 WebRTC offer/answer, ICE candidate 등을 처리
//        String payload = message.getPayload();
//        log.info("Received message: {}", payload);
//
//        // 예시: type이 "offer" 또는 "answer"인 메시지 처리
//        if (payload.contains("offer") || payload.contains("answer")) {
//            // 연결하려는 상대에게 offer/answer 메시지 전달
//            for (WebSocketSession s : sessions) {
//                if (s.isOpen() && !s.getId().equals(session.getId())) {
//                    log.debug("Sending WebRTC offer/answer to {}", s.getId());
//                    try {
//                        s.sendMessage(message);
//                    } catch (IOException e) {
//                        log.error("메시지 전송 실패", e);
//                    }
//                }
//            }
//        }
//
//        // 예시: ICE candidate 처리 (WebRTC의 ICE 후보 전송)
//        else if (payload.contains("candidate")) {
//            // 상대에게 ICE 후보를 전송
//            for (WebSocketSession s : sessions) {
//                if (s.isOpen() && !s.getId().equals(session.getId())) {
//                    log.debug("Sending ICE candidate to {}", s.getId());
//                    try {
//                        s.sendMessage(message);
//                    } catch (IOException e) {
//                        log.error("메시지 전송 실패", e);
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
//        String broadcastId = extractBroadcastId(session);
//        sessions.remove(session);
//
//        if (broadcastId != null && videoStreams.containsKey(broadcastId)){
//            videoStreams.get(broadcastId).close();
//            videoStreams.remove(broadcastId);
//            log.info("방송 저장 완료 : {}", "/videos/" + broadcastId + ".webm");
//        }
//    }
//
//    private String extractBroadcastId(WebSocketSession session) {
//        String query = session.getUri().getQuery();
//        if (query != null && query.contains("broadcastId=")) {
//            return query.split("broadcastId=")[1].split("&")[0]; // broadcastId 값만 추출
//        }
//        return null;
//    }
}
