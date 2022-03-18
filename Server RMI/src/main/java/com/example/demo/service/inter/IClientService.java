package com.example.demo.service.inter;

import com.example.demo.model.Client;
import com.example.demo.model.Manager;
import com.example.demo.model.Restaurant;

import java.util.Collection;
import java.util.List;

public interface IClientService
{
    List<Client> findAll();
    Client findById(Long id);
    Client newClient(Client client);
    Client editClient(Client client);

    void delete(Client client);

    Client findByUsername(String username);
}
