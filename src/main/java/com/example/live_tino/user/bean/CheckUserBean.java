package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.domain.DTO.RequestUserLoginDTO;
import com.example.live_tino.user.domain.UserDAO;
import com.example.live_tino.user.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckUserBean {

    GetUserDAOBean getUserDAOBean;

    @Autowired
    public CheckUserBean(GetUserDAOBean getUserDAOBean){
        this.getUserDAOBean = getUserDAOBean;
    }

    public UserDAO exec(RequestUserLoginDTO requestUserLoginDTO) throws Error {
        return getUserDAOBean.exec(requestUserLoginDTO);
    }
}
