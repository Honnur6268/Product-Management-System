package com.axyya.axjmsp04assignmentB.pmp.service;

import java.util.List;
import java.util.Optional;

import com.axyya.axjmsp04assignmentB.pmp.model.Product;

public interface ProductService {

	List<Product> getProducts();

	Product addProduct(Product product);

	Optional<Product> getProductById(Long id);

	Product updateProduct(Product product);

	void deleteProduct(Long id);
}
