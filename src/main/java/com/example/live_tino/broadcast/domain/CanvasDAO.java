package com.example.live_tino.broadcast.domain;

import com.example.live_tino.broadcast.convert.StringListConvert;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CanvasDAO {
    @Id
    UUID canvasId;

    UUID broadcastId;
    UUID userId;

    @Convert(converter = StringListConvert.class)
    List<Map<String, Object>> canvasList;

    LocalDateTime createAt;
    LocalDateTime uploadAt;
}
