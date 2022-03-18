package com.example.demo.service.inter;

import com.example.demo.model.*;

import java.util.Collection;
import java.util.List;

public interface IRestaurantService
{
    List<Restaurant> findAll();
    Restaurant findById(Long id);
    Collection<Restaurant> findByName(String name);
    Collection<Restaurant> findByNameLike(String name);
    Collection<Restaurant> findByCategory(RestaurantCategory restaurantCategory);
    Collection<Restaurant> findByCity(City city);
    Restaurant save(Restaurant restaurant);
    void delete(Restaurant restaurant);
    Collection<Meal> getMeals(Restaurant restaurant);
    Collection<Comment> getComments(Restaurant restaurant);
}
