package com.example.demo.rmi.inter;

import com.example.demo.model.RestaurantCategory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface IRestaurantCategoryRmiRemote extends Remote
{
    RestaurantCategory findByName(String name) throws RemoteException;
    Collection<RestaurantCategory> findByNameLike(String name) throws RemoteException;
    RestaurantCategory newRestaurantCategory(RestaurantCategory restaurantCategory) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}