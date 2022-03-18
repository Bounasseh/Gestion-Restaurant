package com.example.demo.service.inter;

import com.example.demo.model.Comment;

import java.util.List;

public interface ICommentService
{
    public List<Comment> findAll();
    public Comment findById(Long id);
    Comment save(Comment comment);
    void delete(Comment comment);
}
