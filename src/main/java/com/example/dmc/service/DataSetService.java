package com.example.dmc.service;

import com.example.dmc.entity.DataSet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DataSetService {

    DataSet create(DataSet dataSet) throws IOException;

    DataSet create(MultipartFile dataGetter, MultipartFile dataSplitter, MultipartFile dataSaver, String link) throws IOException;
}
