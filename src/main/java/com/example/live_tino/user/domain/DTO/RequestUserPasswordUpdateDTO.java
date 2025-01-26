package com.example.live_tino.user.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestUserPasswordUpdateDTO {
    UUID userId;
    String userPassword;
}
