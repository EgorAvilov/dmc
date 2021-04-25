package com.example.dmc.service;

import com.example.dmc.entity.Algorithm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AlgorithmService {

    Algorithm create(List<MultipartFile> files,
                     List<String> inputType,
                     List<String> outputType,
                     String name) throws IOException;
    Algorithm create(Algorithm algorithm);

    boolean algorithmExists(Algorithm algorithm);
}
