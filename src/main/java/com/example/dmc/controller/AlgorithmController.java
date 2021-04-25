package com.example.dmc.controller;

import com.example.dmc.entity.Algorithm;
import com.example.dmc.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/algorithms")
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    @Autowired
    public AlgorithmController(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Algorithm algorithm) throws IOException {
       // return new ResponseEntity<>(algorithmService.create(files, inputType, outputType, name), HttpStatus.CREATED);
        return new ResponseEntity<>(algorithmService.create(algorithm),HttpStatus.CREATED);
    }
}



