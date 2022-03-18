package com.example.demo.rmi.impl;

import com.example.demo.model.Client;
import com.example.demo.model.FavoriteRestaurant;
import com.example.demo.rmi.inter.IClientRmiRemote;
import com.example.demo.service.inter.IClientService;
import com.example.demo.service.inter.IFavoriteRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

@Component("clientRmiService")
public class ClientRmiRemoteImpl implements IClientRmiRemote
{
    @Autowired
    IClientService clientService;

    @Autowired
    IFavoriteRestaurantService favoriteRestaurantService;

    @Override
    public List<Client> findAllClients() throws RemoteException
    {
        return clientService.findAll();
    }

    @Override
    public void newClient(Client client) throws RemoteException
    {
        clientService.newClient(client);
    }

    @Override
    public void editClient(Client client) throws RemoteException
    {
        clientService.editClient(client);
    }

    @Override
    public Client findByUsername(String username) throws RemoteException
    {
        return clientService.findByUsername(username);
    }

    @Override
    public Collection<FavoriteRestaurant> getFavoriteRestaurants(Client client) throws RemoteException
    {
        return favoriteRestaurantService.findByClient(client);
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}