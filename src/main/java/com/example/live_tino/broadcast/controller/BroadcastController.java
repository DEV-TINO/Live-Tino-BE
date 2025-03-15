package com.example.live_tino.broadcast.controller;

import com.example.live_tino.broadcast.domain.DTO.*;
import com.example.live_tino.broadcast.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/broadcast")
public class BroadcastController {

    BroadcastService broadcastService;

    @Autowired
    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    // 방송 전체 조회
    // 6개씩 끊어서 주기
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getAllBroadcast(@PathVariable("userId") UUID userId, @RequestParam(value = "page", required = false) Integer page) {

        if (page == null) page = 1;
        Pageable pageable = PageRequest.of(page-1, 6);

        Page<ResponseBroadcastsGetDTO> responseBroadcastsGetDTOList = broadcastService.getAllBroadcast(userId, pageable);

        boolean success = (responseBroadcastsGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "방송 전체 조회 성공" : "방송 전체 조회 실패");
        requestMap.put("broadcastList", responseBroadcastsGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 특정 방송 조회
    @GetMapping("/{broadcastId}")
    public ResponseEntity<Resource> streamVideo(@PathVariable UUID broadcastId, @RequestHeader(value = "Range", required = false) String rangeHeader) throws IOException{
        File videoFile = Paths.get("/videos/" + broadcastId + ".webm").toFile();
        long fileSize = videoFile.length();

        if (!videoFile.exists()){
            return ResponseEntity.notFound().build();
        }

        if (rangeHeader == null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("video/webm"))
                    .body(new FileSystemResource(videoFile));
        }

        String[] ranges = rangeHeader.replace("bytes=", "").split("-");
        long start = Long.parseLong(ranges[0]);
        long end = (ranges.length > 1) ? Long.parseLong(ranges[1]) : Math.min(start + 1024 * 512, fileSize - 1); // 512KB

        byte[] data = new byte[(int) (end - start + 1)];
        try (RandomAccessFile raf = new RandomAccessFile(videoFile, "r")){
            raf.seek(start);
            raf.readFully(data);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "bytes" + start + "-" + end + "/" + fileSize);
        headers.setContentLength(data.length);
        headers.setContentType(MediaType.valueOf("video/webm"));

        return new ResponseEntity<>(new ByteArrayResource(data), headers, HttpStatus.PARTIAL_CONTENT);
    }


    // 방송 참여 인원 조회
    @GetMapping("/{loginId}")
    public ResponseEntity<Map<String, Object>> getAllParticipant(@PathVariable("loginId") String loginId) {

        List<ResponseBroadcastParticipantGetDTO> responseBroadcastParticipantGetDTOList = broadcastService.getAllParticipant(loginId);

        boolean success = (responseBroadcastParticipantGetDTOList == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "참여 인원 전체 조회 성공" : "참여 인원 전체 조회 실패");
        requestMap.put("participantList", responseBroadcastParticipantGetDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 방송 생성
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBroadcast(@RequestBody RequestBroadcastSaveDTO requestBroadcastSaveDTO) {

        UUID broadcastId = broadcastService.createBroadcast(requestBroadcastSaveDTO);

        boolean success = broadcastId != null;

        // Map을 통해 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "방송 생성 성공" : "방송 생성 실패");
        requestMap.put("broadcastId", broadcastId);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 방송 참여
    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> createBroadcastParticipant(@RequestBody RequestBroadcastParticipantSaveDTO requestBroadcastParticipantSaveDTO){

        ResponseBroadcastJoinGetDTO responseBroadcastJoinGetDTO = broadcastService.createBroadcastParticipant(requestBroadcastParticipantSaveDTO);

        boolean success = responseBroadcastJoinGetDTO != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "방송 참여 성공" : "방송 참여 실패");
        requestMap.put("broadcastParticipantId", responseBroadcastJoinGetDTO);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 방송 종료
    @PutMapping("/quit")
    public ResponseEntity<Map<String, Object>> updateQuitBroadcast(@RequestBody RequestBroadcastQuitUpdateDTO requestBroadcastQuitUpdateDTO){

        UUID broadcastId = broadcastService.updateQuitBroadcast(requestBroadcastQuitUpdateDTO);

        boolean success = broadcastId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "방송 종료 성공" : "방송 종료 실패");
        requestMap.put("broadcastId", broadcastId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // canvas 저장


    // 방송 수정
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateBroadcast(@RequestBody RequestBroadcastUpdateDTO requestBroadcastUpdateDTO){
        UUID broadcastId = broadcastService.updateBroadcast(requestBroadcastUpdateDTO);

        boolean success = broadcastId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "방송 수정 성공" : "방송 수정 실패");
        requestMap.put("broadcastId", broadcastId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 방송 삭제
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteBroadcast(@RequestBody RequestBroadcastDeleteDTO requestBroadcastDeleteDTO){
        UUID broadcastId = broadcastService.deleteBroadcast(requestBroadcastDeleteDTO);

        boolean success = broadcastId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "방송 삭제 성공" : "방송 삭제 실패");
        requestMap.put("broadcastId", broadcastId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 사용자 강퇴(삭제)
    @DeleteMapping("/user")
    public ResponseEntity<Map<String, Object>> deleteParticipantBroadcast(@RequestBody RequestBroadcastParticipantDeleteDTO requestBroadcastParticipantDeleteDTO){
        UUID broadcastParticipantId = broadcastService.deleteParticipantBroadcast(requestBroadcastParticipantDeleteDTO);

        boolean success = broadcastParticipantId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "사용자 강퇴(삭제) 성공" : "사용자 강퇴(삭제) 실패");
        requestMap.put("broadcastId", broadcastParticipantId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
