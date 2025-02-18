package com.example.live_tino.user.repository;

import com.example.live_tino.user.domain.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoryJPA extends JpaRepository<UserDAO, UUID> {

    UserDAO findByLoginId(String loginId);

    UserDAO findLoginIdByUserNameAndPhoneNum(String userName, String phoneNum);

    Boolean existsByLoginId(String loginId);
}
