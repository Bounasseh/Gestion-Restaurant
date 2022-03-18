package com.example.demo.rmi.impl;

import com.example.demo.model.RestaurantCategory;
import com.example.demo.rmi.inter.IRestaurantCategoryRmiRemote;
import com.example.demo.service.inter.IRestaurantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.Collection;

@Component("restaurantCategoryRmiService")
public class RestaurantCategoryRmiRemoteImpl implements IRestaurantCategoryRmiRemote
{
    @Autowired
    IRestaurantCategoryService restaurantCategoryService;

    @Override
    public RestaurantCategory findByName(String name) throws RemoteException
    {
        return restaurantCategoryService.findByName(name);
    }

    @Override
    public Collection<RestaurantCategory> findByNameLike(String name) throws RemoteException
    {
        return restaurantCategoryService.findByNameLike(name);
    }

    @Override
    public RestaurantCategory newRestaurantCategory(RestaurantCategory restaurantCategory) throws RemoteException
    {
        return restaurantCategoryService.save(restaurantCategory);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}