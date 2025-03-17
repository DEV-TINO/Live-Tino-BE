package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.UserDAO;
import com.example.live_tino.user.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveUserRefreshTokenDAOBean {

    GetUserDAOBean getUserDAOBean;
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public SaveUserRefreshTokenDAOBean(GetUserDAOBean getUserDAOBean, UserRepositoryJPA userRepositoryJPA){
        this.getUserDAOBean = getUserDAOBean;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public void exec(UUID userId, String refreshToken){
        UserDAO userDAO = getUserDAOBean.exec(userId);
        userDAO.setRefreshToken(refreshToken);
        userRepositoryJPA.save(userDAO);
    }
}
