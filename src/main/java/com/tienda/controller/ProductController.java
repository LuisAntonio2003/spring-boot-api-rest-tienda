package com.tienda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.model.Inventory;
import com.tienda.model.Product;
import com.tienda.repository.InventoryRepository;
import com.tienda.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryRepository inventoryRepository;

	// end point que devuelve todos los productos
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Optional<Product> product = productService.getProductById(id);
		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@SuppressWarnings("unused")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		Optional<Product> optionalProduct = productService.getProductById(id);
		if (optionalProduct.isPresent()) {
			Product existingProduct = optionalProduct.get();
			existingProduct.setName(productDetails.getName());
			existingProduct.setSku(productDetails.getSku());
			existingProduct.setDescription(productDetails.getDescription());
			existingProduct.setPrice(productDetails.getPrice());
			existingProduct.setWeightKg(productDetails.getWeightKg());
			existingProduct.setAvailable(productDetails.getAvailable());
			existingProduct.setImageUrl(productDetails.getImageUrl());
			existingProduct.setCategories(productDetails.getCategories());

			Product updatedProduct = productService.createProduct(existingProduct);
			return ResponseEntity.ok(updatedProduct);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}

	// Endpoint para actualizar inventario
	@PutMapping("/{productId}/inventory")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Long productId,
			@RequestBody Inventory inventoryDetails) {
		Inventory existingInventory = inventoryRepository.findByProductId(productId);
		if (existingInventory != null) {
			if (inventoryDetails.getStock() != null) {
				existingInventory.setStock(inventoryDetails.getStock());
			}
			if (inventoryDetails.getReserved() != null) {
				existingInventory.setReserved(inventoryDetails.getReserved());
			}
			Inventory updatedInventory = inventoryRepository.save(existingInventory);
			return ResponseEntity.ok(updatedInventory);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
