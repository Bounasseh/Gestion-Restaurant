package com.example.demo.rmi.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserRmiRemote extends Remote
{
    boolean isUsernameUsed(String username) throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}
