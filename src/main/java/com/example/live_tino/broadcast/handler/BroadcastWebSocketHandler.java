package com.example.live_tino.broadcast.handler;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.repository.BroadcastRepositoryJPA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class BroadcastWebSocketHandler extends BinaryWebSocketHandler {

    BroadcastRepositoryJPA broadcastRepositoryJPA;
    ConcurrentHashMap<UUID, OutputStream> videoStreams = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        log.info("WebRTC 방송 연결됨 : {}", session.getId());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException{
        UUID broadcastId = UUID.fromString((String) session.getAttributes().get("broadcastId"));
        if (!videoStreams.containsKey(broadcastId)){
            String savePath = "videos/" + broadcastId + ".webm";
            videoStreams.put(broadcastId, new FileOutputStream(savePath));
            log.info("새 방송 파일 생성 : {}", savePath);
        }

        OutputStream outputStream = videoStreams.get(broadcastId);
        outputStream.write(message.getPayload().array());
        outputStream.flush();
        log.info("방송 데이터 저장 중 ");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        UUID broadcastId = UUID.fromString((String) session.getAttributes().get("broadcastId"));

        if (videoStreams.containsKey(broadcastId)){
            videoStreams.get(broadcastId).close();
            videoStreams.remove(broadcastId);

            String videoPath = "videos/" + broadcastId + ".webm";
            BroadcastDAO broadcastDAO = broadcastRepositoryJPA.findById(broadcastId).orElse(null);

            if (broadcastDAO != null) {
                broadcastDAO.setVideoUrl(videoPath);
                broadcastDAO.setIsEnded(true);
                broadcastDAO.setUploadAt(LocalDateTime.now());

                broadcastRepositoryJPA.save(broadcastDAO);
                log.info("방송 종료! 파일 저장 경로 : {}", videoPath);
            }
        }
    }
}
