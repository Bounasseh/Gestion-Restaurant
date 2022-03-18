package com.example.demo.service.impl;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.inter.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements IClientService
{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> findAll()
    {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id)
    {
        return clientRepository.findById(id).get();
    }


    @Override
    public Client newClient(Client client)
    {
        Client newClient = new Client(null, client.getUsername(), client.getEmail(), new BCryptPasswordEncoder().encode(client.getPassword()), null, new Date());
        return clientRepository.save(newClient);
    }

    @Override
    public Client editClient(Client client)
    {
        String password = new BCryptPasswordEncoder().encode(client.getPassword());
        Date createdAt = clientRepository.findById(client.getId()).get().getCreatedAt();
        client.setPassword(password);
        client.setCreatedAt(createdAt);
        return clientRepository.save(client);
    }

    @Override
    public void delete(Client client)
    {
        clientRepository.delete(client);
    }

    @Override
    public Client findByUsername(String username)
    {
        return clientRepository.findByUsername(username);
    }
}