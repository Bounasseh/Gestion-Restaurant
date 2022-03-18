package com.example.demo.repository;

import com.example.demo.model.City;
import com.example.demo.model.Restaurant;
import com.example.demo.model.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RepositoryRestResource
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>
{
    @Modifying
    @Query(value = "truncate table restaurant", nativeQuery = true)
    void truncate();

    Collection<Restaurant> findByName(String name);
    Collection<Restaurant> findByRestaurantCategory(RestaurantCategory restaurantCategory);
    Collection<Restaurant> findByCity(City city);

    Collection<Restaurant> findByNameContainingIgnoreCase(String name);
}
