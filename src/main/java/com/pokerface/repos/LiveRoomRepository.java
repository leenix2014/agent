package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.LiveRoom;

public interface LiveRoomRepository extends JpaRepository<LiveRoom, Long> {

}
