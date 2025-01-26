package com.example.live_tino.chat.repository;

import com.example.live_tino.chat.domain.ChatMessageDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepositoryJPA extends JpaRepository<ChatMessageDAO, UUID> {

    List<ChatMessageDAO> findAllByOrderByCreateAtAsc();
}
