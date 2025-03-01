package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.CreateUserDTOBean;
import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.domain.DTO.ResponseUserGetDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserBean {

    GetUserDAOBean getUserDAOBean;
    CreateUserDTOBean createUserDTOBean;

    @Autowired
    public GetUserBean(GetUserDAOBean getUserDAOBean, CreateUserDTOBean createUserDTOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.createUserDTOBean = createUserDTOBean;
    }

    public ResponseUserGetDTO exec(UUID userId){
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        return createUserDTOBean.exec(userDAO);
    }
}
