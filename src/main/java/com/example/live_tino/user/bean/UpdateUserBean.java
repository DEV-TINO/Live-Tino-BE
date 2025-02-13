package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.CheckUserDAOBean;
import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.bean.small.SaveUserDAOBean;
import com.example.live_tino.user.domain.DTO.RequestUserUpdateDTO;
import com.example.live_tino.user.domain.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UpdateUserBean {

    GetUserDAOBean getUserDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    CheckUserDAOBean checkUserDAOBean;

    @Autowired
    public UpdateUserBean(GetUserDAOBean getUserDAOBean, SaveUserDAOBean saveUserDAOBean, CheckUserDAOBean checkUserDAOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.checkUserDAOBean = checkUserDAOBean;
    }

    public UUID exec(RequestUserUpdateDTO requestUserUpdateDTO){
        UserDAO userDAO = getUserDAOBean.exec(requestUserUpdateDTO.getUserId());
        if (userDAO == null) return null;

        log.info(requestUserUpdateDTO.getNickname());
        log.info(String.valueOf(requestUserUpdateDTO.getUserId()));

        userDAO.setUserName(requestUserUpdateDTO.getUserName());
        userDAO.setNickname(requestUserUpdateDTO.getNickname());
        saveUserDAOBean.exec(userDAO);

        return userDAO.getUserId();
    }

}
