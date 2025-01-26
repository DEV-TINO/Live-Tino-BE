package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.DTO.ResponseUserLoginGetDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateLoginIdDTOBean {

    public ResponseUserLoginGetDTO exec(UserDAO userDAO){
        return ResponseUserLoginGetDTO.builder()
                .loginId(userDAO.getLoginId())
                .build();
    }
}
