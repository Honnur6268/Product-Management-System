package com.axyya.axjmsp04assignmentB.pmp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axyya.axjmsp04assignmentB.pmp.exception.InvalidProductIDException;
import com.axyya.axjmsp04assignmentB.pmp.model.Product;
import com.axyya.axjmsp04assignmentB.pmp.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/productbyId/{id}")
	public Optional<Product> getProductByID(@PathVariable Long id) {

		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) return product;
		else throw new InvalidProductIDException("Product ID "+id+" is NOT FOUND.");
		}

	@GetMapping("/getallProducts")
	public List<Product> getAllProducts() {
		return productService.getProducts();
	}

	@PostMapping("/addProduct")
	@ExceptionHandler(InvalidProductIDException.class)
	public Product addProducts(@RequestBody Product product) {
		Product prod = productService.addProduct(product);

		return prod;
	}

	@PutMapping("/updateProduct")
//	@ExceptionHandler(InvalidProductIDException.class)
	public Product  updateProducts(@RequestBody Product product) {
		Product prod = productService.updateProduct(product);

		return prod;
	}

	@DeleteMapping("/deleteProduct/{id}")
//	@ExceptionHandler(InvalidProductIDException.class)
	public void deleteProducts(@PathVariable Long id) {
		productService.deleteProduct(id);

	}
}
