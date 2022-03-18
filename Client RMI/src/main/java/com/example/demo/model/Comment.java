package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable
{
    private Long id;
    private String comment;
    private Date createdAt;
    private Client client;
    private Restaurant restaurant;
}