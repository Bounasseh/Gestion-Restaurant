package com.example.demo.service.impl;

import com.example.demo.model.RestaurantCategory;
import com.example.demo.repository.RestaurantCategoryRepository;
import com.example.demo.service.inter.IRestaurantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class RestaurantCategoryServiceImpl implements IRestaurantCategoryService
{
    @Autowired
    RestaurantCategoryRepository restaurantCategoryRepository;

    @Override
    public List<RestaurantCategory> findAll()
    {
        return restaurantCategoryRepository.findAll();
    }

    @Override
    public RestaurantCategory findById(Long id)
    {
        return restaurantCategoryRepository.findById(id).get();
    }

    @Override
    public RestaurantCategory findByName(String name)
    {
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return restaurantCategoryRepository.findByCategory(name);
    }

    @Override
    public Collection<RestaurantCategory> findByNameLike(String name)
    {
        return restaurantCategoryRepository.findByCategoryContainingIgnoreCase(name);
    }

    @Override
    public RestaurantCategory save(RestaurantCategory restaurantCategory)
    {
        restaurantCategory.setCategory(restaurantCategory.getCategory().substring(0,1).toUpperCase() + restaurantCategory.getCategory().substring(1).toLowerCase());
        return restaurantCategoryRepository.save(restaurantCategory);
    }

    @Override
    public void delete(RestaurantCategory restaurantCategory)
    {
        restaurantCategoryRepository.delete(restaurantCategory);
    }
}