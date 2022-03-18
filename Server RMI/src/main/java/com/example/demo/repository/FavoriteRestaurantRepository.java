package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.FavoriteRestaurant;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource
public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, Long>
{
    @Modifying
    @Query(value = "truncate table favorite_restaurant", nativeQuery = true)
    void truncate();

    Collection<FavoriteRestaurant> findByClient(Client client);
    FavoriteRestaurant findByClientAndRestaurant(Client client, Restaurant restaurant);
}
