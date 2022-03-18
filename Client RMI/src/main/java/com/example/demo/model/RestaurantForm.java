package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantForm implements Serializable
{
    @NotEmpty
    @Size(min=2, max=50)
    private String name;
    @NotEmpty
    private String description;
    @Pattern(message = "doit être un numéro de téléphone syntaxiquement correcte", regexp="^[0-9]{10}")
    private String phone;
    @NotEmpty
    private String city;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private Date openingTime;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private Date closingTime;
    @NotEmpty
    private String restaurantCategory;
}