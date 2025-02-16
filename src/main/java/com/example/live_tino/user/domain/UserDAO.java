package com.example.live_tino.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDAO {
    @Id
    UUID userId;

    String loginId;
    String userName;
    String nickName;
    String userPassword;
    String phoneNum;

    LocalDateTime createAt;
    LocalDateTime uploadAt;
}
