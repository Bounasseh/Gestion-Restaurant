package com.example.demo.service.inter;

import com.example.demo.model.Rate;

import java.util.List;

public interface IRateService
{
    public List<Rate> findAll();
    public Rate findById(Long id);
    Rate save(Rate rate);
    void delete(Rate rate);
}
