package com.pxp.SQLite.demo.repository;

import com.pxp.SQLite.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

public boolean existsByName(String name);

public List<Category> findByName(String name);

@Query("select max(c.id) from Category c")
public Integer findMaxId();
        }