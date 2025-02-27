package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.CreateUserDAOBean;
import com.example.live_tino.user.bean.small.SaveUserDAOBean;
import com.example.live_tino.user.domain.DTO.RequestUserSaveDTO;
import com.example.live_tino.user.domain.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveUserBean {

    CreateUserDAOBean createUserDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveUserBean(CreateUserDAOBean createUserDAOBean, SaveUserDAOBean saveUserDAOBean){
        this.createUserDAOBean = createUserDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    public UUID exec(RequestUserSaveDTO requestUserSaveDTO){
        UserDAO userDAO = createUserDAOBean.exec(requestUserSaveDTO);
        if (userDAO == null) return null;

        saveUserDAOBean.exec(userDAO);

        return userDAO.getUserId();
    }

}
