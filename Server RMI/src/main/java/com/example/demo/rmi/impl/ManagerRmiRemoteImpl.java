package com.example.demo.rmi.impl;

import com.example.demo.model.Manager;
import com.example.demo.model.Restaurant;
import com.example.demo.rmi.inter.IManagerRmiRemote;
import com.example.demo.service.inter.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

@Component("managerRmiService")
public class ManagerRmiRemoteImpl implements IManagerRmiRemote
{
    @Autowired
    IManagerService managerService;

    @Override
    public List<Manager> findAllManagers() throws RemoteException
    {
        return managerService.findAll();
    }

    @Override
    public void newManager(Manager manager) throws RemoteException
    {
        managerService.newManager(manager);
    }

    @Override
    public void editManager(Manager manager) throws RemoteException
    {
        managerService.editManager(manager);
    }

    @Override
    public Manager findByUsername(String username) throws RemoteException
    {
        return managerService.findByUsername(username);
    }

    @Override
    public Collection<Restaurant> getRestaurants(Manager manager) throws RemoteException
    {
        return managerService.getRestaurants(manager);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}