package com.example.live_tino.broadcast.repository;

import com.example.live_tino.broadcast.domain.BroadcastParticipantDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BroadcastParticipantRepositoryJPA extends JpaRepository<BroadcastParticipantDAO, UUID> {
    List<BroadcastParticipantDAO> findAllByOrderByCreateAtDesc();
}
