package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User implements Serializable
{
    private Collection<FavoriteRestaurant> favoriteRestaurants;
    private Collection<Comment> comments;

    public Client(Long id, String username, String email, String password, String profilePhoto, Date createdAt)
    {
        super(id, username, email, password, profilePhoto, createdAt);
    }
}