package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable, Comparable<Restaurant>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String picture;
    private String phone;
    private Double latitude;
    private Double longitude;
    @ManyToOne
    private City city;
    @Temporal(TemporalType.TIME)
    private Date openingTime;
    @Temporal(TemporalType.TIME)
    private Date closingTime;
    @ManyToOne//(fetch = FetchType.LAZY)
    private RestaurantCategory restaurantCategory;
    @ManyToOne//(fetch = FetchType.LAZY)
    private Manager manager;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private Collection<Meal> meals;
    @OneToMany(mappedBy = "restaurant")
    private Collection<Comment> comments;
    @OneToMany(mappedBy = "restaurant")
    private Collection<FavoriteRestaurant> favoriteRestaurants;
    @Override
    public int compareTo(Restaurant restaurant)
    {
        return this.name.compareTo(restaurant.name);
    }
}