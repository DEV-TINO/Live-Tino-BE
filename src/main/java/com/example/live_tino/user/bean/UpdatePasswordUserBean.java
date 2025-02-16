package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.bean.small.SaveUserDAOBean;
import com.example.live_tino.user.domain.DTO.RequestUserPasswordUpdateDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdatePasswordUserBean {

    GetUserDAOBean getUserDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public UpdatePasswordUserBean(GetUserDAOBean getUserDAOBean, SaveUserDAOBean saveUserDAOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    public UUID exec(RequestUserPasswordUpdateDTO requestUserPasswordUpdateDTO){
        UserDAO userDAO = getUserDAOBean.exec(requestUserPasswordUpdateDTO.getLoginId());
        if(userDAO == null) return null;

        userDAO.setUserPassword(BCrypt.hashpw(requestUserPasswordUpdateDTO.getUserPassword(), BCrypt.gensalt()));

        saveUserDAOBean.exec(userDAO);

        return userDAO.getUserId();
    }
}
