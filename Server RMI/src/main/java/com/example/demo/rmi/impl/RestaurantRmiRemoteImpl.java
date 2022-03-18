package com.example.demo.rmi.impl;

import com.example.demo.model.*;
import com.example.demo.rmi.inter.IRestaurantRmiRemote;
import com.example.demo.service.inter.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.Collection;

@Component("restaurantRmiService")
public class RestaurantRmiRemoteImpl implements IRestaurantRmiRemote
{
    @Autowired
    IRestaurantService restaurantService;

    @Override
    public Restaurant findById(Long id) throws RemoteException
    {
        return restaurantService.findById(id);
    }

    @Override
    public Collection<Restaurant> findAll() throws RemoteException
    {
        return restaurantService.findAll();
    }

    @Override
    public Collection<Restaurant> findByName(String name) throws RemoteException
    {
        return restaurantService.findByName(name);
    }

    @Override
    public Collection<Restaurant> findByNameLike(String name) throws RemoteException
    {
        return restaurantService.findByNameLike(name);
    }

    @Override
    public Collection<Restaurant> findByCategory(RestaurantCategory restaurantCategory) throws RemoteException
    {
        return restaurantService.findByCategory(restaurantCategory);
    }

    @Override
    public Collection<Restaurant> findByCity(City city) throws RemoteException
    {
        return restaurantService.findByCity(city);
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) throws RemoteException
    {
        restaurantService.save(restaurant);
    }

    @Override
    public Collection<Meal> getMeals(Restaurant restaurant) throws RemoteException
    {
        return restaurantService.getMeals(restaurant);
    }

    @Override
    public Collection<Comment> getComments(Restaurant restaurant) throws RemoteException
    {
        return restaurantService.getComments(restaurant);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}