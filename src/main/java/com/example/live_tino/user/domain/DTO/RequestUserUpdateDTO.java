package com.example.live_tino.user.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestUserUpdateDTO {
    UUID userId;
    String userName;
    String nickName;
}
