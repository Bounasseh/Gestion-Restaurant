package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRestaurant implements Serializable
{
    private Long id;
    private Client client;
    private Restaurant restaurant;
}