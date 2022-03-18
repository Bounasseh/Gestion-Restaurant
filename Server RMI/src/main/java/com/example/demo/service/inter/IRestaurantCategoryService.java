package com.example.demo.service.inter;

import com.example.demo.model.RestaurantCategory;

import java.util.Collection;
import java.util.List;

public interface IRestaurantCategoryService
{
    List<RestaurantCategory> findAll();
    RestaurantCategory findById(Long id);
    RestaurantCategory findByName(String name);
    Collection<RestaurantCategory> findByNameLike(String name);
    RestaurantCategory save(RestaurantCategory restaurantCategory);

    void delete(RestaurantCategory restaurantCategory);
}