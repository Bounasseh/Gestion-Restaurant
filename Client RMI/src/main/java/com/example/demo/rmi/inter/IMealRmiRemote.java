package com.example.demo.rmi.inter;

import com.example.demo.model.Meal;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMealRmiRemote extends Remote
{
    Meal findById(Long mealId);
    void saveMeal(Meal meal) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}
