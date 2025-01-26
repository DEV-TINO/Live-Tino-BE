package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.CreateLoginIdDTOBean;
import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.domain.DTO.RequestUserLoginGetDTO;
import com.example.live_tino.user.domain.DTO.ResponseUserLoginGetDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetLoginIdBean {

    GetUserDAOBean getUserDAOBean;
    CreateLoginIdDTOBean createLoginIdDTOBean;

    @Autowired
    public GetLoginIdBean(GetUserDAOBean getUserDAOBean, CreateLoginIdDTOBean createLoginIdDTOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.createLoginIdDTOBean = createLoginIdDTOBean;
    }

    public ResponseUserLoginGetDTO exec(RequestUserLoginGetDTO requestUserLoginGetDTO){
        UserDAO userDAO = getUserDAOBean.exec(requestUserLoginGetDTO.getUserName(), requestUserLoginGetDTO.getPhoneNum());
        if(userDAO == null) return null;

        return createLoginIdDTOBean.exec(userDAO);
    }
}
