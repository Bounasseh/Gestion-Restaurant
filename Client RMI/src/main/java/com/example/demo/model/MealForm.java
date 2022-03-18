package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealForm implements Serializable
{
    @NotEmpty
    @Size(min=2, max=50)
    private String meal;
    @NotNull
    private Double price;
    @NotEmpty
    @Size(min=2, max=50)
    private String mealCategory;
}