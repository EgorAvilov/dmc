package com.example.dmc.controller;

import com.example.dmc.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/datasets")
public class DataSetController {
    private final DataSetService dataSetService;

    @Autowired
    public DataSetController(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestParam("dataGetter") MultipartFile dataGetter,
                                 @RequestParam("dataSplitter") MultipartFile dataSplitter,
                                 @RequestParam("dataSaver") MultipartFile dataSaver,
                                 @RequestParam("link") String link) throws IOException {
        return new ResponseEntity<>(dataSetService.create(dataGetter, dataSplitter, dataSaver, link), HttpStatus.CREATED);
    }
}
