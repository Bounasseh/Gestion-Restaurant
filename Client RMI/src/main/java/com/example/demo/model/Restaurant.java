package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable, Comparable<Restaurant>
{
    private Long id;
    private String name;
    private String description;
    private String picture;
    private String phone;
    private Double latitude;
    private Double longitude;
    private City city;
    private Date openingTime;
    private Date closingTime;
    private RestaurantCategory restaurantCategory;
    private Manager manager;
    private Collection<Meal> meals;
    private Collection<Comment> comments;
    private Collection<FavoriteRestaurant> favoriteRestaurants;

    @Override
    public int compareTo(Restaurant restaurant)
    {
        return this.name.compareTo(restaurant.name);
    }
}