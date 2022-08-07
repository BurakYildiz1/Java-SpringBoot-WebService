package com.webservice.demo.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webservice.demo.model.ProductModel;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {
    public static List<ProductModel> getProducts(){

        List<ProductModel> products=null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/static/ProductData.json"));

            // convert JSON array to list of users
            products = gson.fromJson(reader, new TypeToken<List<ProductModel>>() {}.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products.stream().filter(p->p.isActive()).collect(Collectors.toList());
    }

    //Gönderilen categoryId'ye sahip ürünleri listeler
    public static List<ProductModel> listProducts(int categoryId){

        List<ProductModel> listproducts=null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/static/ProductData.json"));

            // convert JSON array to list of users
            listproducts = gson.fromJson(reader, new TypeToken<List<ProductModel>>() {}.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<ProductModel> collect = listproducts.stream().filter(p -> p.getCategoryId() == categoryId && p.isActive()).collect(Collectors.toList());
        return collect;
    }

    public static List<ProductModel> detailsProducts(int Id){

        List<ProductModel> listproducts=null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/static/ProductData.json"));

            // convert JSON array to list of users
            listproducts = gson.fromJson(reader, new TypeToken<List<ProductModel>>() {}.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<ProductModel> collect = listproducts.stream().filter(p -> p.getId() == Id).collect(Collectors.toList());
        return collect;
    }
}
