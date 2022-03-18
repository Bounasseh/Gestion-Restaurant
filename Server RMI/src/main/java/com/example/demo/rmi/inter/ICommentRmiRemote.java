package com.example.demo.rmi.inter;

import com.example.demo.model.Comment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICommentRmiRemote extends Remote
{
    public List<Comment> findAllComments() throws RemoteException;
    // Test
    String parseSomething() throws RemoteException;
}
