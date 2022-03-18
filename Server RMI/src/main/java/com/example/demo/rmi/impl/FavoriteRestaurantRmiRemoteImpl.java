package com.example.demo.rmi.impl;

import com.example.demo.model.Client;
import com.example.demo.model.FavoriteRestaurant;
import com.example.demo.model.Restaurant;
import com.example.demo.rmi.inter.IFavoriteRestaurantRmiRemote;
import com.example.demo.service.inter.IFavoriteRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component("favoriteRestaurantRmiService")
public class FavoriteRestaurantRmiRemoteImpl implements IFavoriteRestaurantRmiRemote
{
    @Autowired
    IFavoriteRestaurantService favoriteRestaurantService;

    @Override
    public FavoriteRestaurant findById(Long favoriteRestaurantId) throws RemoteException
    {
        return favoriteRestaurantService.findById(favoriteRestaurantId);
    }

    @Override
    public FavoriteRestaurant findByClientAndRestaurant(Client client, Restaurant restaurant) throws RemoteException
    {
        return favoriteRestaurantService.findByClientAndRestaurant(client, restaurant);
    }

    @Override
    public Boolean inFavoriteRestaurants(Client client, Restaurant restaurant) throws RemoteException
    {
        return favoriteRestaurantService.inFavoriteRestaurants(client, restaurant);
    }

    @Override
    public void addFavoriteRestaurant(Client client, Restaurant restaurant) throws RemoteException
    {
        favoriteRestaurantService.addFavoriteRestaurant(client, restaurant);
    }

    @Override
    public void removeFavoriteRestaurantById(FavoriteRestaurant favoriteRestaurant)
    {
        favoriteRestaurantService.delete(favoriteRestaurant);
    }

    @Override
    public void removeFavoriteRestaurant(FavoriteRestaurant favoriteRestaurant) throws RemoteException
    {
        favoriteRestaurantService.removeFavoriteRestaurant(favoriteRestaurant);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}