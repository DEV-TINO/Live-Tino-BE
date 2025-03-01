package com.example.live_tino.user.domain.DTO;

import jakarta.servlet.http.Cookie;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseUserLoginDTO {
    Cookie[] cookies;
    UUID userId;
    String userName;
}
