package com.example.demo.rmi.impl;

import com.example.demo.rmi.inter.IUserRmiRemote;
import com.example.demo.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component("userRmiService")
public class UserRmiRemote implements IUserRmiRemote
{
    @Autowired
    IUserService userService;

    @Override
    public boolean isUsernameUsed(String username) throws RemoteException
    {
        return userService.isUsernameUsed(username);
    }

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}
