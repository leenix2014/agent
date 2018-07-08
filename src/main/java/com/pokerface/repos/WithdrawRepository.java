package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {

}
