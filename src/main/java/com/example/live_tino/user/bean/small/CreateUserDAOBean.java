package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.DTO.RequestUserSaveDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateUserDAOBean {

    public UserDAO exec(RequestUserSaveDTO requestUserSaveDTO){
        return UserDAO.builder()
                .userId(UUID.randomUUID())
                .loginId(requestUserSaveDTO.getLoginId())
                .userName(requestUserSaveDTO.getUserName())
                .nickname(requestUserSaveDTO.getNickname())
                .userPassword(BCrypt.hashpw(requestUserSaveDTO.getUserPassword(), BCrypt.gensalt()))
                .phoneNum(requestUserSaveDTO.getPhoneNum())
                .createAt(LocalDateTime.now())
                .uploadAt(LocalDateTime.now())
                .build();
    }
}
