package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.UserDAO;
import com.example.live_tino.user.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public SaveUserDAOBean(UserRepositoryJPA userRepositoryJPA){
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public void exec(UserDAO userDAO) {
        userRepositoryJPA.save(userDAO);
    }
}
