package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.LiveName;

public interface LiveNameRepository extends JpaRepository<LiveName, Long> {

}
