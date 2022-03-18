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
public class Manager extends User implements Serializable
{
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    Collection<Restaurant> restaurants;

    public Manager(Long id, String username, String email, String password, String profilePhoto, Date createdAt)
    {
        super(id, username, email, password, profilePhoto, createdAt);
    }
}