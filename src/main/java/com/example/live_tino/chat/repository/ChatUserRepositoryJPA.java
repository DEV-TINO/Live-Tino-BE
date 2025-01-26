package com.example.live_tino.chat.repository;

import com.example.live_tino.chat.domain.ChatUserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatUserRepositoryJPA extends JpaRepository<ChatUserDAO, UUID> {

    ChatUserDAO findByChatRoomIdAndUserId(UUID chatRoomId, UUID userId);
}
