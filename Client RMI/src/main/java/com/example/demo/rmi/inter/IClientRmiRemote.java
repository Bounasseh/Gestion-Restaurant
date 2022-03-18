package com.example.demo.rmi.inter;

import com.example.demo.model.Client;
import com.example.demo.model.FavoriteRestaurant;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

public interface IClientRmiRemote extends Remote
{
    List<Client> findAllClients() throws RemoteException;
    void newClient(Client client) throws RemoteException;
    void editClient(Client client) throws RemoteException;
    Client findByUsername(String username) throws RemoteException;
    Collection<FavoriteRestaurant> getFavoriteRestaurants(Client client) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}