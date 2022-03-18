package com.example.demo.repository;

import com.example.demo.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource
public interface RateRepository extends JpaRepository<Rate, Long>
{
    @Modifying
    @Query(value = "truncate table rate", nativeQuery = true)
    void truncate();
}
