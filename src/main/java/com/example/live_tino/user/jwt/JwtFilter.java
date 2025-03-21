package com.example.live_tino.user.jwt;

import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.bean.small.SaveUserRefreshTokenDAOBean;
import com.example.live_tino.user.domain.UserDAO;
import com.example.live_tino.user.repository.UserRepositoryJPA;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private String secretKey;
    private GetUserDAOBean getUserDAOBean;
    UserRepositoryJPA userRepositoryJPA;
    SaveUserRefreshTokenDAOBean saveUserRefreshTokenDAOBean;

    @Autowired
    public JwtFilter(UserRepositoryJPA userRepositoryJPA, SaveUserRefreshTokenDAOBean saveUserRefreshTokenDAOBean){
        this.userRepositoryJPA = userRepositoryJPA;
        this.saveUserRefreshTokenDAOBean = saveUserRefreshTokenDAOBean;
    }

    public JwtFilter(String secretKey, GetUserDAOBean getUserDAOBean){
        this.secretKey = secretKey;
        this.getUserDAOBean = getUserDAOBean;
    }

    private String getTokenFromCookies(Cookie[] cookies, String tokenName) {
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if (tokenName.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public void addToken(HttpServletResponse response, String tokenName, String token, int maxAge){
        Cookie cookie = new Cookie(tokenName, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String accessToken = getTokenFromCookies(request.getCookies(), "access_token");
        final String refreshToken = getTokenFromCookies(request.getCookies(), "refresh_token");

        log.info("AccessToken from cookies : {}", accessToken);
        log.info("RefreshToken from cookies : {}", refreshToken);

        try {
            if (accessToken != null && !accessToken.isEmpty() && !JwtUtil.isExpired(accessToken, secretKey)) {
                authenticateUser(accessToken, request);
            }
        } catch (ExpiredJwtException e) {
            if (refreshToken != null && !refreshToken.isEmpty() && !JwtUtil.isExpired(refreshToken, secretKey)) {
                // AccessToken이 없지만 RefreshToken이 유효한 경우
                String userId = JwtUtil.getUserId(refreshToken, secretKey);

                String newAccessToken = JwtUtil.createAccessToken(UUID.fromString(userId), secretKey);
                addToken(response, "access_token", newAccessToken, 60 * 60 * 24);

                String newRefreshToken = JwtUtil.createRefreshToken(UUID.fromString(userId), secretKey);
                addToken(response, "refresh_token", newRefreshToken, 60 * 60 * 24);

                saveUserRefreshTokenDAOBean.exec(UUID.fromString(userId), newRefreshToken);

                authenticateUser(newAccessToken, request);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token, HttpServletRequest request) {
        String userId = JwtUtil.getUserId(token, secretKey);
        log.info("userId : {}", userId);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
