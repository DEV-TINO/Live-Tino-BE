package com.example.live_tino.broadcast.repository;

import com.example.live_tino.broadcast.domain.BroadcastDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface BroadcastRepositoryJPA extends JpaRepository<BroadcastDAO, UUID> {

    List<BroadcastDAO> findAllByOrderByCreateAtAsc();
}