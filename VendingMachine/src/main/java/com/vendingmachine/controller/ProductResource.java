package com.vendingmachine.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingmachine.entity.Machine;
import com.vendingmachine.entity.Product;
import com.vendingmachine.service.ProductService;


@RestController
@RequestMapping("/{machineId}/products")
public class ProductResource {
	
	
	@Autowired
	ProductService productService;

    /**
     * Get the products
     *
     * @param machineId the name of the vending machine
     * @return all the products in that vending machine
     */
    @GetMapping  
    ResponseEntity<Collection<Product>> getProducts(@PathVariable String machineId) {    	
    	return ResponseEntity.ok(productService.getProducts(machineId));        
    }

    /**
     * Buy a specific item from a specific machine
     *
     * @param machineId the vending machine
     * @param productId the product
     * @return the product you have purchased
     */
    @GetMapping("/{productId}/buy")    
    ResponseEntity<Machine> buy(@PathVariable String machineId, @PathVariable Long productId) {    	
    	return ResponseEntity.ok(productService.purchaseProduct(machineId, productId));
    }

    /**
     * Add a new product
     *
     * @param machineId the vending machine name
     * @param input     the product
     * @return the newly added product
     */
    @PostMapping
    @RequestMapping(produces={MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Product> add(@PathVariable String machineId, @RequestBody Product product) {        
    	
        Product createdProduct = productService.addNewProduct(machineId, product);
        return ResponseEntity.ok(createdProduct);

       

    }

    /**
     * Get a specific product
     *
     * @param machineId the vending machine name
     * @param productId the product id
     * @return the product
     */
    @GetMapping("/{productId}")       
    ResponseEntity<Product> getProduct(@PathVariable String machineId, @PathVariable Long productId) {    	
    	return ResponseEntity.ok(productService.getProduct(machineId, productId));
       
    }

   
}
