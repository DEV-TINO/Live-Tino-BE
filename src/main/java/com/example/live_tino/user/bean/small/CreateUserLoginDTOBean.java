package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.DTO.ResponseUserLoginDTO;
import com.example.live_tino.user.domain.UserDAO;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CreateUserLoginDTOBean {

    public ResponseUserLoginDTO exec(UserDAO userDAO, Cookie[] cookies){
        return ResponseUserLoginDTO.builder()
                .cookies(cookies)
                .userName(userDAO.getUserName())
                .userId(userDAO.getUserId())
                .build();
    }
}
