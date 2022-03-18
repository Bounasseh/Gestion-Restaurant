package com.example.demo.rmi.impl;

import com.example.demo.rmi.inter.IAppInitRmiRemote;
import com.example.demo.service.inter.IAppInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component("appInitRmiService")
public class AppInitRmiRemoteImpl implements IAppInitRmiRemote
{
    @Autowired
    IAppInitService appInitService;

    @Override
    public void insertData() throws RemoteException
    {
        appInitService.insertAllData();
    }

    @Override
    public void deleteData() throws RemoteException
    {
        System.out.println("AFFIRMATIF");
        appInitService.deleteAllData();
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}