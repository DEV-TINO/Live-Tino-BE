package com.example.live_tino.broadcast.repository;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface BroadcastRepositoryJPA extends JpaRepository<BroadcastDAO, UUID> {

    Page<BroadcastDAO> findAllByOrderByCreateAtAsc(Pageable pageable);

    BroadcastDAO findByUserId(UUID userId);

    // 유저 ID로 진행중인 방송 있는 지 확인
    boolean existsByUserIdAndIsEndedFalse(UUID userId);

    String findTitleByBroadcastId(UUID broadcastId);
}