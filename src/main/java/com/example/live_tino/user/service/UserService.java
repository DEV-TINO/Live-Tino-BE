package com.example.live_tino.user.service;

import com.example.live_tino.user.bean.*;
import com.example.live_tino.user.domain.DTO.*;
import com.example.live_tino.user.domain.UserDAO;
import com.example.live_tino.user.Error;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {

    SaveUserBean saveUserBean;
    CheckUserBean checkUserBean;
    AddCookieBean addCookieBean;
    GetCookieBean getCookieBean;
    UpdatePasswordUserBean updatePasswordUserBean;
    UpdateUserBean updateUserBean;
    GetLoginIdBean getLoginIdBean;
    CheckUserLoginIdBean checkUserLoginIdBean;

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Autowired
    public UserService(SaveUserBean saveUserBean, CheckUserBean checkUserBean, AddCookieBean addCookieBean, GetCookieBean getCookieBean, UpdatePasswordUserBean updatePasswordUserBean, UpdateUserBean updateUserBean, GetLoginIdBean getLoginIdBean, CheckUserLoginIdBean checkUserLoginIdBean){
        this.saveUserBean = saveUserBean;
        this.checkUserBean = checkUserBean;
        this.addCookieBean = addCookieBean;
        this.getCookieBean = getCookieBean;
        this.updatePasswordUserBean = updatePasswordUserBean;
        this.updateUserBean = updateUserBean;
        this.getLoginIdBean = getLoginIdBean;
        this.checkUserLoginIdBean = checkUserLoginIdBean;
    }

    // 로그인
    public Cookie[] login(RequestUserLoginDTO requestUserLoginDTO) throws Error {
        UserDAO userDAO = checkUserBean.exec(requestUserLoginDTO);
        return addCookieBean.exec(userDAO, secretKey);
    }


    // 로그아웃
    public Cookie logout(HttpServletRequest request){
        return getCookieBean.exec(request);
    }


    // 유저 저장
    public UUID saveUser(RequestUserSaveDTO requestUserSaveDTO){
        return saveUserBean.exec(requestUserSaveDTO);
    }


    // 비밀번호 찾기(수정)
    public UUID updatePasswordUser(RequestUserPasswordUpdateDTO RequestUserPasswordUpdateDTO){
        return updatePasswordUserBean.exec(RequestUserPasswordUpdateDTO);
    }


    // 유저 정보 수정
    public UUID updateUser(RequestUserUpdateDTO requestUserUpdateDTO) throws Error {
        return updateUserBean.exec(requestUserUpdateDTO);
    }


    // 아이디 찾기
    public ResponseUserLoginGetDTO getLoginId(RequestUserLoginGetDTO requestUserLoginGetDTO){
        return getLoginIdBean.exec(requestUserLoginGetDTO);
    }

    // 아이디 중복 확인
    public Boolean duplicateLoginId(RequestUserDuplicateLoginIdDTO requestUserDuplicateLoginIdDTO){
        return checkUserLoginIdBean.exec(requestUserDuplicateLoginIdDTO);
    }


    // 유저 인증

}
