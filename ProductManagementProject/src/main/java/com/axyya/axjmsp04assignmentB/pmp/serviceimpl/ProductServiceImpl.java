package com.axyya.axjmsp04assignmentB.pmp.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axyya.axjmsp04assignmentB.pmp.exception.InvalidProductIDException;
import com.axyya.axjmsp04assignmentB.pmp.model.Product;
import com.axyya.axjmsp04assignmentB.pmp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
//	List<Product> products = new ArrayList<>();
	List<Product> products = new ArrayList<>(Arrays.asList(new Product(100001L, "Wings of Fire", "Books", 140.00),
			new Product(100002L, "Doll", "Toys", 240.00),
			new Product(100003L, "Ignited Minds: Unleashing the Power within India", "Books", 540.00)));

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		if (products.isEmpty()) {
			throw new InvalidProductIDException("Product list is empty.");
		}
		return products;
	}

	@Override
	public Product addProduct(Product product) {
		Optional<Product> exisitingProduct = getProductById(product.getId());
		if (!exisitingProduct.isPresent()) {
			products.add(product);
		} else {
			throw new InvalidProductIDException("Product details with id " + product.getId() + " is already present.");
		}
		return product;
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		Optional<Product> product = products.stream().filter(prod -> prod.getId().equals(id)).findAny();
		return product;

	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> exisitingProduct = getProductById(product.getId());
		if (exisitingProduct.isPresent()) {

			for (int i = 0; i < products.size(); i++) {
				Product getProductId = products.get(i);
				if (getProductId.getId().equals(product.getId())) {
					products.set(i, product);
				}
			}
		} else {
			throw new InvalidProductIDException(
					"Product with code " + product.getId() + " is Not Found. Please try to update with correct Product ID.");
		}
		return product;

	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> exisitingProduct = getProductById(id);

		if (exisitingProduct.isPresent()) {

			for (int i = 0; i < products.size(); i++) {
				Product getProductId = products.get(i);
				if (getProductId.getId().equals(id)) {
					products.remove(i);
				}
			}
		} else {
			throw new InvalidProductIDException("Product with code " + id + " is Not Found.");
		}

	}

}
