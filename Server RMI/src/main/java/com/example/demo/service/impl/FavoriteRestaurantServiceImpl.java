package com.example.demo.service.impl;

import com.example.demo.model.Client;
import com.example.demo.model.FavoriteRestaurant;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.FavoriteRestaurantRepository;
import com.example.demo.service.inter.IFavoriteRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class FavoriteRestaurantServiceImpl implements IFavoriteRestaurantService
{
    @Autowired
    FavoriteRestaurantRepository favoriteRestaurantRepository;

    @Override
    public List<FavoriteRestaurant> findAll()
    {
        return favoriteRestaurantRepository.findAll();
    }

    @Override
    public FavoriteRestaurant findById(Long id)
    {
        return favoriteRestaurantRepository.findById(id).get();
    }

    @Override
    public FavoriteRestaurant findByClientAndRestaurant(Client client, Restaurant restaurant)
    {
        return favoriteRestaurantRepository.findByClientAndRestaurant(client, restaurant);
    }

    @Override
    public FavoriteRestaurant save(FavoriteRestaurant favoriteRestaurant)
    {
        return favoriteRestaurantRepository.save(favoriteRestaurant);
    }

    @Override
    public void delete(FavoriteRestaurant favoriteRestaurant)
    {
        favoriteRestaurantRepository.delete(favoriteRestaurant);
    }

    @Override
    public Collection<FavoriteRestaurant> findByClient(Client client)
    {
        return favoriteRestaurantRepository.findByClient(client);
    }

    @Override
    public Boolean inFavoriteRestaurants(Client client, Restaurant restaurant)
    {
        return favoriteRestaurantRepository.findByClientAndRestaurant(client, restaurant) != null;
    }

    @Override
    public void addFavoriteRestaurant(Client client, Restaurant restaurant)
    {
        FavoriteRestaurant favoriteRestaurant = new FavoriteRestaurant(null, client, restaurant);
        favoriteRestaurantRepository.save(favoriteRestaurant);
    }

    @Override
    public void removeFavoriteRestaurant(FavoriteRestaurant favoriteRestaurant)
    {
        favoriteRestaurantRepository.delete(favoriteRestaurant);
    }
}