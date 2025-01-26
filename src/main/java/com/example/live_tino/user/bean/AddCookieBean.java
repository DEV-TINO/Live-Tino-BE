package com.example.live_tino.user.bean;

import com.example.live_tino.user.bean.small.CreateCookieBean;
import com.example.live_tino.user.domain.UserDAO;
import com.example.live_tino.user.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddCookieBean {

    CreateCookieBean createCookieBean;

    @Autowired
    public AddCookieBean(CreateCookieBean createCookieBean) {
        this.createCookieBean = createCookieBean;
    }

    public Cookie[] exec(UserDAO userDAO, String secretKey) {
        Cookie[] cookies = new Cookie[2];

        cookies[0] = createCookieBean.exec("access_token", JwtUtil.createAccessToken(userDAO.getUserId(), secretKey));
        cookies[1] = createCookieBean.exec("refresh_token", JwtUtil.createRefreshToken(userDAO.getUserId(), secretKey));


        log.info("AccessToken: {}", cookies[0].getValue());
        log.info("RefreshToken: {}", cookies[1].getValue());


        return cookies;
    }
}
