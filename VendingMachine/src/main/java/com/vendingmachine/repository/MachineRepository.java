package com.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingmachine.entity.Machine;

import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    /**
     * Find all machines with a specific name
     *
     * @param name name of the machine
     * @return if the machine exists
     */
    Optional<Machine> findByName(String name);

    /**
     * Find the machine with a a specific name
     *
     * @param name name of the machine
     * @return the machine
     */
    Machine findOneByName(String name);
}
