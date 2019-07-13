package com.vendingmachine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendingmachine.configure.MachineNotFoundException;
import com.vendingmachine.entity.Machine;
import com.vendingmachine.repository.MachineRepository;

@Service
public class MachineService {
	
	@Autowired
	MachineRepository machineRepository;
	
	
	public List<Machine> getAllMachines(){
		return machineRepository.findAll();		
	}	
	
	public Machine getMachine(String machineId){		
		return isMachineExists(machineId);
			
	}
	
	public Machine save(Machine machine){		
		return this.machineRepository.save(new Machine(machine.name,machine.currentAmount));		
	}
	
	public Machine saveAndFlush(Machine machine){		
		return this.machineRepository.saveAndFlush(machine);		
	}
	
	
   /**
     * Check if a given machine name exists
     *
     * @param machineId the machine name
     */
    public Machine isMachineExists(String machineId) {
    	Machine machine = machineRepository.findOne(Long.parseLong(machineId));
    	if(machine == null){
    		throw new  MachineNotFoundException(machineId);
    	}   
    	return machine;
    }	

}
