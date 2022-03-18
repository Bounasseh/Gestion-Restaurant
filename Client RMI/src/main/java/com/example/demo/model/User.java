package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User implements Serializable
{
    private Long id;
    @NotEmpty
    @Size(min=2, max=50)
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min=3, max=50)
    private String password;
    private String profilePhoto;
    private Date createdAt;
}