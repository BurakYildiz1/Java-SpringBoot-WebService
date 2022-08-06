package com.webservice.demo.controller;

import com.webservice.demo.model.ProductModel;
import com.webservice.demo.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/products")
    public String Index() {
        return "Products Works!";
    }

    @PostMapping("/products")
    public ProductModel Index(ProductModel ProductModel){
        return ProductModel;
    }

    @GetMapping("/getProducts")
    public List<ProductModel> GetProducts(){
        return ProductRepository.getProducts();
    }

}