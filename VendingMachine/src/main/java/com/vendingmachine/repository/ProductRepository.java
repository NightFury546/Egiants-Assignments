package com.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingmachine.entity.Product;

import java.util.Collection;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Find products in a vending machine
     *
     * @param name the vending machine name
     * @return if the machine exists
     */
    Collection<Product> findByMachineName(String name);
}
