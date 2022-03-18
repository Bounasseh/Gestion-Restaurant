package com.example.demo.rmi.impl;

import com.example.demo.model.Comment;
import com.example.demo.rmi.inter.ICommentRmiRemote;
import com.example.demo.service.inter.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.List;

@Component("commentRmiService")
public class CommentRmiRemoteImpl implements ICommentRmiRemote
{
    @Autowired
    ICommentService commentService;

    @Override
    public List<Comment> findAllComments() throws RemoteException
    {
        return commentService.findAll();
    }

    // Test

    @Override
    public String parseSomething() throws RemoteException
    {
        return "*** Something ***";
    }
}