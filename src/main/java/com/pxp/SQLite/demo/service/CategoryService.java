package com.pxp.SQLite.demo.service;

import com.pxp.SQLite.demo.entity.Category;
import com.pxp.SQLite.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public String createCategory(Category category){
        try {
            if (!categoryRepository.existsByName(category.getName())){
                category.setId(null == categoryRepository.findMaxId()? 0 : categoryRepository.findMaxId() + 1);
                categoryRepository.save(category);
                return "Category record created successfully.";
            }else {
                return "Category already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<Category> readCategories(){
        return categoryRepository.findAll();
    }


   /*public String updateCategory(Category category){
      /*  if (categoryRepository.existsByName(category.getName())){
            try {
                List<Category> categories = categoryRepository.findAllById(category.getId());
                categories.stream().forEach(s -> {
                    Category categoryToBeUpdate = categoryRepository.findById(s.getId()).get();
                    categoryToBeUpdate.setName(categories.get());

                    categoryRepository.save(categoryToBeUpdate);
                });
                return "Category record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Category does not exists in the database.";
        }
    }*/

    @Transactional
    public String deleteCategory(Category category){
        if (categoryRepository.existsByName(category.getName())){
            try {
                List<Category> categories =categoryRepository.findByName(category.getName());
                categories.stream().forEach(s -> {
                    categoryRepository.delete(s);
                });
                return "Category record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Category does not exist";
        }
    }
}

