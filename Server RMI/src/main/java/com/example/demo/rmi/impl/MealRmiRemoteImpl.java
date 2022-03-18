package com.example.demo.rmi.impl;

import com.example.demo.model.Meal;
import com.example.demo.rmi.inter.IMealRmiRemote;
import com.example.demo.service.inter.IMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component("mealRmiService")
public class MealRmiRemoteImpl implements IMealRmiRemote
{
    @Autowired
    IMealService mealService;

    @Override
    public Meal findById(Long mealId) throws RemoteException
    {
        return mealService.findById(mealId);
    }

    @Override
    public void saveMeal(Meal meal) throws RemoteException
    {
        mealService.save(meal);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}