package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Network;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository

public interface NetworkRepository extends JpaRepository<Network, Integer> {
}
