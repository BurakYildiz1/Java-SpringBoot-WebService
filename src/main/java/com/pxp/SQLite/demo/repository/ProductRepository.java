package com.pxp.SQLite.demo.repository;

import com.pxp.SQLite.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public boolean existsByName(String name);
    public List<Product> findByName(String name);

    @Query("select max(p.id) from Product p")
    public Integer findMaxId();

}