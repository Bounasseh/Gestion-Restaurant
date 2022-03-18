package com.example.demo.rmi.inter;

import com.example.demo.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface IRestaurantRmiRemote extends Remote
{
    Collection<Restaurant> findAll() throws RemoteException;
    Restaurant findById(Long id) throws RemoteException;
    Collection<Restaurant> findByName(String name) throws RemoteException;
    Collection<Restaurant> findByNameLike(String name) throws RemoteException;
    Collection<Restaurant> findByCategory(RestaurantCategory restaurantCategory) throws RemoteException;
    Collection<Restaurant> findByCity(City city) throws RemoteException;
    void saveRestaurant(Restaurant restaurant) throws RemoteException;
    Collection<Meal> getMeals(Restaurant restaurant) throws RemoteException;
    Collection<Comment> getComments(Restaurant restaurant) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}
