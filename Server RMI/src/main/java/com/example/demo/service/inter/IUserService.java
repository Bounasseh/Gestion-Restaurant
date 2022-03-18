package com.example.demo.service.inter;

import com.example.demo.model.User;

import java.util.List;

public interface IUserService
{
    public List<User> findAll();
    public User findById(Long id);
    public User save(User user);
    public void delete(User user);
    public boolean isUsernameUsed(String username);
}