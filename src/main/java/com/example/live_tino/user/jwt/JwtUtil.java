package com.example.live_tino.user.jwt;

import com.example.live_tino.user.bean.small.GetUserDAOBean;
import com.example.live_tino.user.bean.small.SaveUserRefreshTokenDAOBean;
import com.example.live_tino.user.domain.UserDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    GetUserDAOBean getUserDAOBean;
    SaveUserRefreshTokenDAOBean saveUserRefreshTokenDAOBean;

    @Autowired
    public JwtUtil(GetUserDAOBean getUserDAOBean, SaveUserRefreshTokenDAOBean saveUserRefreshTokenDAOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.saveUserRefreshTokenDAOBean = saveUserRefreshTokenDAOBean;
    }

    public static String createAccessToken(UUID userId, String secretKey){
        Claims claims = Jwts.claims();
        claims.put("userId", userId.toString());

        long date = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(date))
                .setExpiration(new Date(date + 3600000L))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static String createRefreshToken(UUID userId, String secretKey){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        long date = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(date))
                .setExpiration(new Date(date + 86400000L))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createAndStoreRefreshToken(UUID userId, String secretKey) {
        String refreshToken = createRefreshToken(userId, secretKey);

        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO != null) {
            saveUserRefreshTokenDAOBean.exec(userId, refreshToken);
        }
        return refreshToken;
    }


    public static String getUserId(String token, String secretKey){
        return extractClaims(token, secretKey).get("userId").toString();
    }

    public static boolean isExpired(String token, String secretKey){
        Date expiredDate = extractClaims(token, secretKey).getExpiration();

        return expiredDate.before(new Date());
    }

    private static Claims extractClaims(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
