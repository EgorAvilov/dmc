package com.example.dmc.controller;

import com.example.dmc.entity.Algorithm;
import com.example.dmc.entity.User;
import com.example.dmc.service.AlgorithmService;
import com.example.dmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/algorithms")
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    @Autowired
    public AlgorithmController(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody List<Algorithm> algorithms) {
        return new ResponseEntity<>(algorithmService.create(algorithms), HttpStatus.CREATED);
    }
}
