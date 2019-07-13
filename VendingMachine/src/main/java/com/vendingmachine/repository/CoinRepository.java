package com.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingmachine.entity.Coin;

import java.util.Collection;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
    /**
     * Find all coins in a given machine
     *
     * @param name
     * @return
     */
    Collection<Coin> findByMachineName(String name);
}
