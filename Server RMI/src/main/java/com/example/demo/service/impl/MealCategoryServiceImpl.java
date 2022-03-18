package com.example.demo.service.impl;

import com.example.demo.model.MealCategory;
import com.example.demo.repository.MealCategoryRepository;
import com.example.demo.service.inter.IMealCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MealCategoryServiceImpl implements IMealCategoryService
{
    @Autowired
    MealCategoryRepository mealCategoryRepository;

    @Override
    public List<MealCategory> findAll()
    {
        return mealCategoryRepository.findAll();
    }

    @Override
    public MealCategory findById(Long id)
    {
        return mealCategoryRepository.findById(id).get();
    }

    @Override
    public MealCategory findByName(String name)
    {
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return mealCategoryRepository.findByCategory(name);
    }

    @Override
    public MealCategory save(MealCategory mealCategory)
    {
        mealCategory.setCategory(mealCategory.getCategory().substring(0,1).toUpperCase() + mealCategory.getCategory().substring(1).toLowerCase());
        return mealCategoryRepository.save(mealCategory);
    }

    @Override
    public void delete(MealCategory mealCategory)
    {
        mealCategoryRepository.delete(mealCategory);
    }
}