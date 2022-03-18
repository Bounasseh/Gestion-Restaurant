package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Comment;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository
@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long>
{
    @Modifying
    @Query(value = "truncate table comment", nativeQuery = true)
    void truncate();

    Collection<Comment> findByClient(Client client);
    Collection<Comment> findByRestaurant(Restaurant restaurant);
}