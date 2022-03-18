package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
public class UserServiceImpl implements IUserService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id)
    {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user)
    {
        userRepository.delete(user);
    }

    @Override
    public boolean isUsernameUsed(String username)
    {
        AtomicBoolean isUsernameUsed = new AtomicBoolean(false);
        userRepository.findAll().forEach(user ->
        {
            if (user.getUsername().equals(username))
            {
                isUsernameUsed.set(true);
                return;
            }
        });
        return isUsernameUsed.get();
    }
}