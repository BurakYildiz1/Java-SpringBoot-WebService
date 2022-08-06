package com.webservice.demo.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webservice.demo.model.CategoryModel;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryRepository {
    public static List<CategoryModel> getCategories(){
        List<CategoryModel> categories = null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/static/CategoryData.json"));

            // convert JSON array to list of categories
            categories = gson.fromJson(reader, new TypeToken<List<CategoryModel>>() {}.getType());

            // return categories

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categories.stream().filter(p->p.isActive()).collect(Collectors.toList());
    }
}
