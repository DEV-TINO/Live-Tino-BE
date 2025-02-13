package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.CheckUserDAOBean;
import com.example.live_tino.user.domain.DTO.RequestUserDuplicateLoginIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckUserLoginIdBean {
    CheckUserDAOBean checkUserDAOBean;

    @Autowired
    public CheckUserLoginIdBean(CheckUserDAOBean checkUserDAOBean){
        this.checkUserDAOBean = checkUserDAOBean;
    }

    public Boolean exec(RequestUserDuplicateLoginIdDTO requestUserDuplicateLoginIdDTO){
        return checkUserDAOBean.exec(requestUserDuplicateLoginIdDTO);
    }
}
