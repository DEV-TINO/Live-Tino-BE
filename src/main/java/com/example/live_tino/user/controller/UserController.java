package com.example.live_tino.user.controller;

import com.example.live_tino.user.domain.DTO.*;
import com.example.live_tino.user.error;
import com.example.live_tino.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody RequestUserLoginDTO requestUserLoginDTO, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = null;
        Map<String, Object> requestMap = new HashMap<>();
        try {
            cookies = userService.login(requestUserLoginDTO);
        } catch (error e) {
            // throw new RuntimeException(e);
            requestMap.put("success", false);
            requestMap.put("message", e.getMsg());
            requestMap.put("errorType", e.getType());
            return ResponseEntity.status(HttpStatus.OK).body(requestMap);
        }

        log.info(cookies[0].toString());
        log.info(cookies[1].toString());

        // Map 이용해서 success, 메시지와 id 값 json 데이터로 변환
        // Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", cookies != null);
        requestMap.put("message", cookies != null ? "유저 로그인 성공" : "유저 로그인 실패");

        // TODO: For Debug..
        requestMap.put(cookies[0].getName(), cookies[0].getValue());
        requestMap.put(cookies[1].getName(), cookies[1].getValue());

        if(cookies != null) {
            for(Cookie cookie : cookies) {
                httpServletResponse.addCookie(cookie);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logoutUser(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = userService.logout(request);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", cookie != null);
        requestMap.put("message", cookie != null ? "로그아웃 성공" : "로그아웃 실패");

        if (cookie != null){
            Cookie c = new Cookie("access_token", "");
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
        }

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 유저 저장
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody RequestUserSaveDTO requestUserSaveDTO) {
        UUID userId = userService.saveUser(requestUserSaveDTO);

        Boolean success = userId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "유저 저장 성공" : "유저 저장 실패");
        requestMap.put("userId", userId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 비밀번호 찾기(수정)
    @PutMapping("/password")
    public ResponseEntity<Map<String, Object>> updatePasswordUser(@RequestBody RequestUserPasswordUpdateDTO requestUserPasswordUpdateDTO){
        UUID userId = userService.updatePasswordUser(requestUserPasswordUpdateDTO);

        boolean success = userId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "비밀번호 수정 성공" : "비밀번호 수정 실패");
        requestMap.put("userId", userId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 유저 정보 수정
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody RequestUserUpdateDTO requestUserUpdateDTO){
        UUID userId = userService.updateUser(requestUserUpdateDTO);

        boolean success = userId != null;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "유저 정보 수정 성공" : "유저 정보 수정 실패");
        requestMap.put("userId", userId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 아이디 찾기
    @PostMapping("/id")
    public ResponseEntity<Map<String, Object>> getLoginId(@RequestBody RequestUserLoginGetDTO requestUserLoginGetDTO){
        ResponseUserLoginGetDTO responseUserLoginGetDTO = userService.getLoginId(requestUserLoginGetDTO);

        boolean success = (responseUserLoginGetDTO == null) ? false : true;

        // Map 이용해서 메시지와 id 값 json 데이터로 변환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "아이디 찾기 성공" : "아이디어 찾기 실패");
        requestMap.put("loginInfo", responseUserLoginGetDTO);

        // status, body 설정해서 응답 리턴
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 유저 인증


}