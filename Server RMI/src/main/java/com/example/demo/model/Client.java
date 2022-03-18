package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User implements Serializable
{
    @OneToMany(mappedBy = "client")
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "client")
    private Collection<FavoriteRestaurant> favoriteRestaurants;

    public Client(Long id, String username, String email, String password, String profilePhoto, Date createdAt)
    {
        super(id, username, email, password, profilePhoto, createdAt);
    }
}