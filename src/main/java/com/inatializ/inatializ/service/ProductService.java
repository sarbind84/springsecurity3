package com.inatializ.inatializ.service;

import com.inatializ.inatializ.dto.ProductDto;
import com.inatializ.inatializ.entity.Products;
import com.inatializ.inatializ.entity.UserInfo;
import com.inatializ.inatializ.repository.ProductRepository;
import com.inatializ.inatializ.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> products = productRepository.findAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Products> getByProductsId(long id) {
        try {
            Optional<Products> product = productRepository.findById(id);
            if (product.isPresent()) {
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<HttpStatus> save(ProductDto productDto) {
        try {
            Products product = new Products();
            BeanUtils.copyProperties(productDto, product);
            productRepository.save(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<HttpStatus> update(ProductDto productDto, Long id) {
        try {
            Optional<Products> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                Products existingProduct = productOptional.get();
                BeanUtils.copyProperties(productDto, existingProduct, "id");
                productRepository.save(existingProduct);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<HttpStatus> deleteById(long id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}
