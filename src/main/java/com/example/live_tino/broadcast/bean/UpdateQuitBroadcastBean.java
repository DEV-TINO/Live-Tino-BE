package com.example.live_tino.broadcast.bean;

import com.example.live_tino.broadcast.bean.small.GetBroadcastDAOBean;
import com.example.live_tino.broadcast.bean.small.SaveBroadcastDAOBean;
import com.example.live_tino.broadcast.domain.BroadcastDAO;
import com.example.live_tino.broadcast.domain.DTO.RequestBroadcastQuitUpdateDTO;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class UpdateQuitBroadcastBean {

    GetBroadcastDAOBean getBroadcastDAOBean;
    SaveBroadcastDAOBean saveBroadcastDAOBean;

    @Autowired
    public UpdateQuitBroadcastBean(GetBroadcastDAOBean getBroadcastDAOBean, SaveBroadcastDAOBean saveBroadcastDAOBean){
        this.getBroadcastDAOBean = getBroadcastDAOBean;
        this.saveBroadcastDAOBean = saveBroadcastDAOBean;
    }

    public UUID exec(RequestBroadcastQuitUpdateDTO requestBroadcastQuitUpdateDTO){
        BroadcastDAO broadcastDAO = getBroadcastDAOBean.exec(requestBroadcastQuitUpdateDTO.getBroadcastId());
        if (broadcastDAO == null) return null;

        broadcastDAO.setUploadAt(LocalDateTime.now());

        String totalTime = calculateTotalTime(broadcastDAO.getCreateAt(), broadcastDAO.getUploadAt());
        broadcastDAO.setTotalTime(totalTime);

        String saveDate = calculateSaveDate(broadcastDAO.getUploadAt());

        broadcastDAO.setSaveDate(saveDate);

        broadcastDAO.setBroadcastId(requestBroadcastQuitUpdateDTO.getBroadcastId());

        broadcastDAO.setIsEnded(true);

        saveBroadcastDAOBean.exec(broadcastDAO);

        return broadcastDAO.getBroadcastId();
    }

    private String calculateTotalTime(LocalDateTime createAt, LocalDateTime uploadAt){
        if (createAt == null || uploadAt == null) return  "99:99:99";

        Duration duration = Duration.between(createAt, uploadAt);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private String calculateSaveDate(LocalDateTime uploadAt){
        if (uploadAt == null) return "99.99.99";

        return uploadAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
