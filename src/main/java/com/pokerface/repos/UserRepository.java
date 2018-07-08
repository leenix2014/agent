package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
