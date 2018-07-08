package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
