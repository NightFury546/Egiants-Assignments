package com.vendingmachine.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendingmachine.configure.ProductNotFoundException;
import com.vendingmachine.entity.Machine;
import com.vendingmachine.entity.Product;
import com.vendingmachine.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	MachineService machineService;
	
	public Collection<Product> getProducts(String machineId){		
		Machine machine = machineService.isMachineExists(machineId);
		return machine.getProducts();		
	}
	
	public Product getProduct(String machineId,Long productId){	
		
		final Product[] soldProduct = new Product[1];
		Machine machine = machineService.isMachineExists(machineId);
		
		machine.getProducts().forEach(product -> {
			if (product.getId() == productId) {
				soldProduct[0] = product;
			}
		});		
		
		return soldProduct[0];		
	}
	
	public Machine purchaseProduct(String machineId,Long productId){	
		
		final boolean[] productExists = new boolean[1]; 
		final Product[] soldProduct = new Product[1];
		Machine machine = machineService.getMachine(machineId);      

		machine.getProducts().forEach(product -> {
			if (product.getId() == productId
					&& product.cost <= machine.currentAmount
					&& product.quantity > 0) {
				product.quantity--;
				this.productRepository.saveAndFlush(product);
				machine.currentAmount -= product.cost;				
				soldProduct[0] = product;
				productExists[0] = true;
			}
		});
		
		if(!productExists[0]){
			throw new ProductNotFoundException(productId);
		}
		
		return machineService.saveAndFlush(machine);


	}	
	
	public Product addNewProduct(String machineId,Product newProduct){		

		final boolean[] productExists = new boolean[1];   
		final Product[] soldProduct = new Product[1];
        Machine machine = machineService.getMachine(machineId);      
        
        machine.getProducts().forEach(product -> {
			if (product.getName().equalsIgnoreCase(newProduct.getName())) {
				product.quantity +=  newProduct.quantity;
				this.productRepository.save(product);	
				productExists[0] = true;
				soldProduct[0] = product;
			}
		});
        
        if(!productExists[0]){
        	soldProduct[0] = productRepository.save(new Product(machine, newProduct.name, newProduct.cost, newProduct.quantity));
        }
          
        return soldProduct[0];
    
	}

}
