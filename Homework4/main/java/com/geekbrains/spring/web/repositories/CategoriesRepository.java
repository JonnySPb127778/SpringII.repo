package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

 //   @Query("select c from Category c where c.title = :categoryTitle")
    Category getByTitle(String categoryTitle);
}
