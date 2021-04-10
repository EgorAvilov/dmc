package com.example.dmc.service.impl;

import com.example.dmc.entity.Algorithm;
import com.example.dmc.repository.AlgorithmRepository;
import com.example.dmc.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    private final AlgorithmRepository algorithmRepository;

    @Autowired
    public AlgorithmServiceImpl(AlgorithmRepository algorithmRepository) {
        this.algorithmRepository = algorithmRepository;
    }

    @Override
    public List<Algorithm> create(List<Algorithm> algorithms) {
        return algorithmRepository.saveAll(algorithms);
    }
}
