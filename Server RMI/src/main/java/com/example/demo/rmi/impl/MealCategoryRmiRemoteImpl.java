package com.example.demo.rmi.impl;

import com.example.demo.model.MealCategory;
import com.example.demo.rmi.inter.IMealCategoryRmiRemote;
import com.example.demo.service.inter.IMealCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component("mealCategoryRmiService")
public class MealCategoryRmiRemoteImpl implements IMealCategoryRmiRemote
{
    @Autowired
    IMealCategoryService mealCategoryService;

    @Override
    public MealCategory findByName(String name) throws RemoteException
    {
        return mealCategoryService.findByName(name);
    }

    @Override
    public MealCategory newMealCategory(MealCategory mealCategory) throws RemoteException
    {
        return mealCategoryService.save(mealCategory);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}