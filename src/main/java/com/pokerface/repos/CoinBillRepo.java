package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.CoinBill;

public interface CoinBillRepo extends JpaRepository<CoinBill, Long> {

}
