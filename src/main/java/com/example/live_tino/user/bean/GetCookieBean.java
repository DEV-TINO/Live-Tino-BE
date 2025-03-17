package com.example.live_tino.user.bean;

import com.example.live_tino.user.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class GetCookieBean {

    public Cookie exec(HttpServletRequest request){
        String cookieName = "access_token";

        Cookie[] cookieList = request.getCookies();

        Cookie cookie = null;

        if(cookieList != null){
            for(Cookie c : cookieList){
                if (cookieName.equals(c.getName())){
                    cookie = c;
                    break;
                }
            }
        }

        // 쿠키가 존재하는 지 확인
        if (cookie == null) {
            return null;
        }

        return cookie;
    }
}
