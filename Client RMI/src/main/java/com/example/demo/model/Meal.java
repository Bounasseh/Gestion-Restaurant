package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal implements Serializable
{
    private Long id;
    private String meal;
    private Double price;
    private Restaurant restaurant;
    private MealCategory mealCategory;
}