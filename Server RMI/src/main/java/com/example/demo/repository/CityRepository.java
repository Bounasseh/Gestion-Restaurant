package com.example.demo.repository;

import com.example.demo.model.City;
import com.example.demo.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource
public interface CityRepository extends JpaRepository<City, Long>
{
    @Modifying
    @Query(value = "truncate table city", nativeQuery = true)
    void truncate();

    City findByCity(String city);
    Collection<City> findByCityContainingIgnoreCase(String city);
}
