package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.LiveWord;

public interface LiveWordRepository extends JpaRepository<LiveWord, Long> {

}
