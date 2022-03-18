package com.example.demo.service.impl;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.inter.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService
{
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findAll()
    {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id)
    {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment save(Comment comment)
    {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment)
    {
        commentRepository.delete(comment);
    }
}