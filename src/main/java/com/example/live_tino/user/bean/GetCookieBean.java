package com.example.live_tino.user.bean;

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

        return cookie;
    }
}
