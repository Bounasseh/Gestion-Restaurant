package com.example.demo.repository;

import com.example.demo.model.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource
public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long>
{
    @Modifying
    @Query(value = "truncate table restaurant_category", nativeQuery = true)
    void truncate();

    RestaurantCategory findByCategory(String category);

    Collection<RestaurantCategory> findByCategoryContainingIgnoreCase(String name);
}
