package com.pxp.SQLite.demo.controller;

import com.pxp.SQLite.demo.entity.Category;
import com.pxp.SQLite.demo.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String Info(){
        return "The application is up!";
    }


    @RequestMapping(value = "/createcategory",method = RequestMethod.POST)
    public String createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @RequestMapping(value = "/categorylist",method = RequestMethod.GET)
    public List<Category> getCategoryList(){
        return categoryService.readCategories();
    }

  // @RequestMapping(value = "/updatecategory",method = RequestMethod.PUT)
  /*  public String uptadeCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
*/

    @RequestMapping(value = "/deletecategory",method = RequestMethod.DELETE)
    public String deleteCategory(@RequestBody Category category){
        return categoryService.deleteCategory(category);
    }

}
