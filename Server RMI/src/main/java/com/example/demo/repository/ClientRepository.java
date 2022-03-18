package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long>
{
    @Modifying
    @Query(value = "truncate table client", nativeQuery = true)
    void truncateCity();

    Client findByUsername(String username);
}