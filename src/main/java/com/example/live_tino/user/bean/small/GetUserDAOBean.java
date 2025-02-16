package com.example.live_tino.user.bean.small;

import com.example.live_tino.user.domain.DTO.RequestUserLoginDTO;
import com.example.live_tino.user.domain.UserDAO;
import com.example.live_tino.user.Error;
import com.example.live_tino.user.repository.UserRepositoryJPA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class GetUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public GetUserDAOBean(UserRepositoryJPA userRepositoryJPA){
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public UserDAO exec(UUID userId){
        return userRepositoryJPA.findById(userId).orElse(null);
    }

    public UserDAO exec(String loginId){
        return userRepositoryJPA.findByLoginId(loginId);
    }

    public UserDAO exec(String userName, String phoneNum){
        return userRepositoryJPA.findLoginIdByUserNameAndPhoneNum(userName, phoneNum);
    }

    public UserDAO exec(RequestUserLoginDTO requestUserLoginDTO) throws Error {
        String loginId = requestUserLoginDTO.getLoginId();
        String userPassword = requestUserLoginDTO.getUserPassword();

        log.info(loginId);
        log.info(userPassword);

        UserDAO userDAO = userRepositoryJPA.findByLoginId(loginId);

        // accountId로 조회했을 때 값이 없는 경우
        if(userDAO == null) {
            throw new Error("아이디를 찾을 수 없습니다.", "2000");
            // return null;
        };

        // 암호화된 비밀번호를 확인했을 때 일치한다면 true, 다르다면 false
        boolean flag = BCrypt.checkpw(userPassword, userDAO.getUserPassword());
//        return flag ? userDAO : null;
        if (flag) {
            log.info("Check pwd success" );
            return userDAO;
        } else {
            throw new Error("비밀번호를 찾을 수 없습니다.", "2001");

        }
    }
}
