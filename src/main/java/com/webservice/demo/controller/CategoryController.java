package com.webservice.demo.controller;

import com.webservice.demo.model.CategoryModel;
import com.webservice.demo.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("/category")
    public String Index() {
        return "Category Works!";
    }

    @GetMapping("/getCategories")
    public List<CategoryModel> GetCategories(){
        return CategoryRepository.getCategories();
    }


}