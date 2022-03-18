package com.example.demo.service.impl;

import com.example.demo.model.Meal;
import com.example.demo.repository.MealRepository;
import com.example.demo.service.inter.IMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MealServiceImpl implements IMealService
{
    @Autowired
    MealRepository mealRepository;

    @Override
    public List<Meal> findAll()
    {
        return mealRepository.findAll();
    }

    @Override
    public Meal findById(Long id)
    {
        return mealRepository.findById(id).get();
    }

    @Override
    public Meal save(Meal meal)
    {
        return mealRepository.save(meal);
    }

    @Override
    public void delete(Meal meal)
    {
        mealRepository.delete(meal);
    }
}