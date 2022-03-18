package com.example.demo.repository;

import com.example.demo.model.MealCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource
public interface MealCategoryRepository extends JpaRepository<MealCategory, Long>
{
    @Modifying
    @Query(value = "truncate table meal_category", nativeQuery = true)
    void truncate();

    MealCategory findByCategory(String category);
}
