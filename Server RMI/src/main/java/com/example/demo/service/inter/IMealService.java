package com.example.demo.service.inter;

import com.example.demo.model.Meal;

import java.util.List;

public interface IMealService
{
    List<Meal> findAll();
    Meal findById(Long id);
    Meal save(Meal meal);
    void delete(Meal meal);
}
