package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.RoomConfig;

public interface RoomRepository extends JpaRepository<RoomConfig, Long> {

}
