package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.bean.small.SaveUserDAOBean;
import com.example.live_tino.user.domain.DTO.RequestUserUpdateDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateUserBean {

    GetUserDAOBean getUserDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public UpdateUserBean(GetUserDAOBean getUserDAOBean, SaveUserDAOBean saveUserDAOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    public UUID exec(RequestUserUpdateDTO requestUserUpdateDTO){
        UserDAO userDAO = getUserDAOBean.exec(requestUserUpdateDTO.getUserId());
        if (userDAO == null) return null;

        userDAO.setUserName(requestUserUpdateDTO.getUserName());
        userDAO.setNickname(requestUserUpdateDTO.getNickName());

        saveUserDAOBean.exec(userDAO);

        return userDAO.getUserId();
    }

}
