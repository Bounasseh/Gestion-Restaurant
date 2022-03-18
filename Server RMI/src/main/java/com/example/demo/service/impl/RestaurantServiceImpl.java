package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.inter.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements IRestaurantService
{
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Restaurant> findAll()
    {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id)
    {
        return restaurantRepository.findById(id).get();
    }

    @Override
    public Collection<Restaurant> findByName(String name)
    {
        return restaurantRepository.findByName(name);
    }

    @Override
    public Collection<Restaurant> findByNameLike(String name)
    {
        return restaurantRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Collection<Restaurant> findByCategory(RestaurantCategory restaurantCategory)
    {
        return restaurantRepository.findByRestaurantCategory(restaurantCategory);
    }

    @Override
    public Collection<Restaurant> findByCity(City city)
    {
        return restaurantRepository.findByCity(city);
    }

    @Override
    public Restaurant save(Restaurant restaurant)
    {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Restaurant restaurant)
    {
        restaurantRepository.delete(restaurant);
    }

    @Override
    public Collection<Meal> getMeals(Restaurant restaurant)
    {
        return null;
    }

    @Override
    public Collection<Comment> getComments(Restaurant restaurant)
    {
        return commentRepository.findByRestaurant(restaurant);
    }
}
