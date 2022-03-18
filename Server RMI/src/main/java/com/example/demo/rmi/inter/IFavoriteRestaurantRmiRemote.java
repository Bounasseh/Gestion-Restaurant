package com.example.demo.rmi.inter;

import com.example.demo.model.Client;
import com.example.demo.model.FavoriteRestaurant;
import com.example.demo.model.Restaurant;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFavoriteRestaurantRmiRemote extends Remote
{
    FavoriteRestaurant findById(Long favoriteRestaurantId) throws RemoteException;
    FavoriteRestaurant findByClientAndRestaurant(Client client, Restaurant restaurant) throws RemoteException;
    Boolean inFavoriteRestaurants(Client client, Restaurant restaurant) throws RemoteException;
    void addFavoriteRestaurant(Client client, Restaurant restaurant) throws RemoteException;
    void removeFavoriteRestaurantById(FavoriteRestaurant favoriteRestaurant) throws RemoteException;
    void removeFavoriteRestaurant(FavoriteRestaurant favoriteRestaurant) throws RemoteException;

    // Test

    String parseSomething() throws RemoteException;
}