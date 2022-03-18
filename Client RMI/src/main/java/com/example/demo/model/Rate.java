package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate implements Serializable
{
    private Long id;
    private int rate;
    private Client client;
    private Restaurant restaurant;
}