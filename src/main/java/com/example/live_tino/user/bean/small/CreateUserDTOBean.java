package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.DTO.ResponseUserGetDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDTOBean {
    public ResponseUserGetDTO exec(UserDAO userDAO){
        return ResponseUserGetDTO.builder()
                .loginId(userDAO.getLoginId())
                .userName(userDAO.getUserName())
                .phoneNum(userDAO.getPhoneNum())
                .build();
    }
}
