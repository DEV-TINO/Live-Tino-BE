package com.example.live_tino.user.domain.DTO;

import lombok.Data;

@Data
public class RequestUserSaveDTO {
    String loginId;
    String userName;
    String userPassword;
    String phoneNum;
}
