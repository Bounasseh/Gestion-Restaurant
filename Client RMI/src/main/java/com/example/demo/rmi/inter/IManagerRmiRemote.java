package com.example.demo.rmi.inter;

import com.example.demo.model.Manager;
import com.example.demo.model.Restaurant;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface IManagerRmiRemote extends Remote
{
    List<Manager> findAllManagers() throws RemoteException;
    void newManager(Manager manager) throws RemoteException;
    void editManager(Manager manager) throws RemoteException;
    public Manager findByUsername(String username) throws RemoteException;
    Collection<Restaurant> getRestaurants(Manager manager) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}