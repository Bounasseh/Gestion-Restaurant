package com.example.demo.service.inter;

import com.example.demo.model.Client;
import com.example.demo.model.FavoriteRestaurant;
import com.example.demo.model.Restaurant;

import java.util.Collection;
import java.util.List;

public interface IFavoriteRestaurantService
{
    public List<FavoriteRestaurant> findAll();
    public FavoriteRestaurant findById(Long id);
    FavoriteRestaurant findByClientAndRestaurant(Client client, Restaurant restaurant);
    FavoriteRestaurant save(FavoriteRestaurant favoriteRestaurant);
    void delete(FavoriteRestaurant favoriteRestaurant);
    Collection<FavoriteRestaurant> findByClient(Client client);
    Boolean inFavoriteRestaurants(Client client, Restaurant restaurant);
    void addFavoriteRestaurant(Client client, Restaurant restaurant);
    void removeFavoriteRestaurant(FavoriteRestaurant favoriteRestaurant);
}
