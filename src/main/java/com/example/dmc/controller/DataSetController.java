package com.example.dmc.controller;

import com.example.dmc.entity.DataSet;
import com.example.dmc.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/datasets")
public class DataSetController {
    private final DataSetService dataSetService;

    @Autowired
    public DataSetController(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DataSet dataSet) {
        return new ResponseEntity<>(dataSetService.create(dataSet), HttpStatus.CREATED);
    }
}
