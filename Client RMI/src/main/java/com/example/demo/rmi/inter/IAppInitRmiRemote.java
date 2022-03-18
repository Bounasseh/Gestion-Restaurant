package com.example.demo.rmi.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAppInitRmiRemote extends Remote
{
    void insertData() throws RemoteException;
    void deleteData() throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}