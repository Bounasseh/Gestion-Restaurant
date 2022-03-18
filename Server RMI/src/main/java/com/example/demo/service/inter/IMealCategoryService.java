package com.example.demo.service.inter;

import com.example.demo.model.MealCategory;

import java.util.List;

public interface IMealCategoryService
{
    List<MealCategory> findAll();
    MealCategory findById(Long id);
    MealCategory findByName(String name);
    MealCategory save(MealCategory mealCategory);
    void delete(MealCategory mealCategory);
}
