package com.example.demo.rmi.inter;

import com.example.demo.model.City;
import com.example.demo.model.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface ICityRmiRemote extends Remote
{
    City findByName(String name) throws RemoteException;
    Collection<City> findByNameLike(String name) throws RemoteException;
    City newCity(City city) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}