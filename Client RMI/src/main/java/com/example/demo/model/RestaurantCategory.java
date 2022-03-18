package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCategory implements Serializable
{
    private Long id;
    private String category;
    private Collection<Restaurant> restaurants;
}