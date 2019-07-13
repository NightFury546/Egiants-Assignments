package com.vendingmachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vendingmachine.entity.Machine;
import com.vendingmachine.service.MachineService;


@RestController
public class MachineResource {
    

    @Autowired
    MachineService machineService;

    /**
     * Get all machines
     *
     * @return all machines
     */
    @GetMapping   
    ResponseEntity<List<Machine>> getMachines() {
    	return ResponseEntity.ok(machineService.getAllMachines());        
    }

    /**
     * Add a new machine to the system
     *
     * @param input the machine you want to add
     * @return the added machine
     */
    @PostMapping    
    ResponseEntity<Machine> add(@RequestBody Machine machine) {

        Machine createdMachine = machineService.save(machine);
        return ResponseEntity.ok(createdMachine);

    }

    /**
     * Get the details of a specific vending machine
     *
     * @param machineId the machine name
     * @return the machine details
     */
    @GetMapping("/{machineId}")    
    ResponseEntity<Machine> readMachine(@PathVariable String machineId) {      
        return ResponseEntity.ok(machineService.getMachine(machineId));  
    }

   
}
