package com.example.demo.service.impl;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.inter.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements ICityService
{
    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> findAll()
    {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id)
    {
        return cityRepository.findById(id).get();
    }

    @Override
    public City findByName(String name)
    {
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return cityRepository.findByCity(name);
    }

    @Override
    public Collection<City> findByNameLike(String name)
    {
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return cityRepository.findByCityContainingIgnoreCase(name);
    }

    @Override
    public City save(City city)
    {
        city.setCity(city.getCity().substring(0,1).toUpperCase() + city.getCity().substring(1).toLowerCase());
        return cityRepository.save(city);
    }

    @Override
    public void delete(City city)
    {
        cityRepository.delete(city);
    }
}