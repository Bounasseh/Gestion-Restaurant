package com.example.demo.rmi.inter;

import com.example.demo.model.MealCategory;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMealCategoryRmiRemote extends Remote
{
    MealCategory findByName(String name) throws RemoteException;
    MealCategory newMealCategory(MealCategory mealCategory) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}