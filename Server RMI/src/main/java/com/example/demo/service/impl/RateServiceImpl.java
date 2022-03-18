package com.example.demo.service.impl;

import com.example.demo.model.Rate;
import com.example.demo.repository.RateRepository;
import com.example.demo.service.inter.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RateServiceImpl implements IRateService
{
    @Autowired
    RateRepository rateRepository;

    @Override
    public List<Rate> findAll()
    {
        return rateRepository.findAll();
    }

    @Override
    public Rate findById(Long id)
    {
        return rateRepository.findById(id).get();
    }

    @Override
    public Rate save(Rate rate)
    {
        return rateRepository.save(rate);
    }

    @Override
    public void delete(Rate rate)
    {
        rateRepository.delete(rate);
    }
}