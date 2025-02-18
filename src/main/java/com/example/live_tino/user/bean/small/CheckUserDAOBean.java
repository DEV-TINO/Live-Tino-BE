package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.DTO.RequestUserDuplicateLoginIdDTO;
import com.example.live_tino.user.domain.DTO.RequestUserUpdateDTO;
import com.example.live_tino.user.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public CheckUserDAOBean(UserRepositoryJPA userRepositoryJPA){
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public Boolean exec(RequestUserDuplicateLoginIdDTO requestUserDuplicateLoginIdDTO){
        return userRepositoryJPA.existsByLoginId(requestUserDuplicateLoginIdDTO.getLoginId());
    }
}
