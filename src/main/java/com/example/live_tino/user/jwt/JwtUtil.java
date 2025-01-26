package com.example.live_tino.user.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    public static long date = System.currentTimeMillis();

    public static String createAccessToken(UUID userId, String secretKey){
        Claims claims = Jwts.claims();
        claims.put("userId", userId.toString());

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

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(date))
                .setExpiration(new Date(date + 86400000L))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
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
