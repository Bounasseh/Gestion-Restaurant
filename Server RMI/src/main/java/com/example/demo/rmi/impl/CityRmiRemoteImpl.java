package com.example.demo.rmi.impl;

import com.example.demo.model.City;
import com.example.demo.rmi.inter.ICityRmiRemote;
import com.example.demo.service.inter.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.Collection;

@Component("cityRmiService")
public class CityRmiRemoteImpl implements ICityRmiRemote
{
    @Autowired
    ICityService cityService;

    @Override
    public City findByName(String name) throws RemoteException
    {
        return cityService.findByName(name);
    }

    @Override
    public Collection<City> findByNameLike(String name) throws RemoteException
    {
        return cityService.findByNameLike(name);
    }

    @Override
    public City newCity(City city) throws RemoteException
    {
        return cityService.save(city);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}