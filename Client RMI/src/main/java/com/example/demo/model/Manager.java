package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User implements Serializable
{
    Collection<Restaurant> restaurants;

    public Manager(Long id, String username, String email, String password, String profilePhoto, Date createdAt)
    {
        super(id, username, email, password, profilePhoto, createdAt);
    }
}