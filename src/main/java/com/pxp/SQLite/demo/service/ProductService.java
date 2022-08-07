package com.pxp.SQLite.demo.service;

import com.pxp.SQLite.demo.entity.Product;
import com.pxp.SQLite.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public String createProduct(Product product){
        try {
            if (!productRepository.existsById(product.getId())){
                product.setId(null == productRepository.findMaxId()? 0 : productRepository.findMaxId() + 1);
                productRepository.save(product);
                return "Product record created successfully.";
            }else {
                return "Product already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<Product> readProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public String updateProduct(Product product){
        if (productRepository.existsByName(product.getName())){
            try {
                List<Product> products = productRepository.findByName(product.getName());
                products.stream().forEach(s -> {
                    Product productToBeUpdate = productRepository.findById(s.getId()).get();
                    productToBeUpdate.setName(product.getName());
                    productToBeUpdate.setImageUrl(product.getImageUrl());
                    productToBeUpdate.setCategoryId(product.getCategoryId());
                    productRepository.save(productToBeUpdate);
                });
                return "Product record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Product does not exists in the database.";
        }
    }

    @Transactional
    public String deleteProduct(Product product){
        if (productRepository.existsByName(product.getName())){
            try {
                List<Product> products = productRepository.findByName(product.getName());
                products.stream().forEach(s -> {
                    productRepository.delete(s);
                });
                return "Product record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Product does not exist";
        }
    }

}
