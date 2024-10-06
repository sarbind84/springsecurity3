package com.inatializ.inatializ.controller;

import com.inatializ.inatializ.dto.ProductDto;
import com.inatializ.inatializ.entity.Products;
import com.inatializ.inatializ.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable long id) {
        return productService.getByProductsId(id);
    }

    // Add new product
    @PostMapping
    public ResponseEntity<HttpStatus> addProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    // Update existing product
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.update(productDto, id);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable long id) {
        return productService.deleteById(id);
    }


}

