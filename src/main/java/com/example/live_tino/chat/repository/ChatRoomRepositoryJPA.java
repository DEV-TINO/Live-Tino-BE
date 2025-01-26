package com.example.live_tino.chat.repository;

import com.example.live_tino.chat.domain.ChatRoomDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepositoryJPA extends JpaRepository<ChatRoomDAO, UUID> {
}
