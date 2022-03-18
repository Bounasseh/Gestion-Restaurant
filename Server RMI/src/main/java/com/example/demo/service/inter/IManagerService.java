package com.example.demo.service.inter;

import com.example.demo.model.Manager;
import com.example.demo.model.Restaurant;

import java.util.Collection;
import java.util.List;

public interface IManagerService
{
    List<Manager> findAll();
    Manager findById(Long id);
    Manager newManager(Manager manager);
    Manager editManager(Manager manager);
    void delete(Manager manager);

    Manager findByUsername(String username);
    Collection<Restaurant> getRestaurants(Manager manager);
}
