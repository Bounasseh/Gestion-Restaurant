package com.example.demo.service.impl;

import com.example.demo.model.Manager;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.inter.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements IManagerService
{
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public List<Manager> findAll()
    {
        return managerRepository.findAll();
    }

    @Override
    public Manager findById(Long id)
    {
        return managerRepository.findById(id).get();
    }

    @Override
    public Manager newManager(Manager manager)
    {
        Manager newManager = new Manager(null, manager.getUsername(), manager.getEmail(), new BCryptPasswordEncoder().encode(manager.getPassword()), null, new Date());
        return managerRepository.save(newManager);
    }

    @Override
    public Manager editManager(Manager manager)
    {
        String password = new BCryptPasswordEncoder().encode(manager.getPassword());
        Date createdAt = managerRepository.findById(manager.getId()).get().getCreatedAt();
        manager.setPassword(password);
        manager.setCreatedAt(createdAt);
        return managerRepository.save(manager);
    }

    @Override
    public void delete(Manager manager)
    {
        managerRepository.delete(manager);
    }

    @Override
    public Manager findByUsername(String username)
    {
        return managerRepository.findByUsername(username);
    }

    @Override
    public Collection<Restaurant> getRestaurants(Manager manager)
    {
        return manager.getRestaurants();
    }
}