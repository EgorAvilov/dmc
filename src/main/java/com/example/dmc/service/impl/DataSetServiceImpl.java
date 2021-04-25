package com.example.dmc.service.impl;

import com.example.dmc.entity.DataSet;
import com.example.dmc.entity.Task;
import com.example.dmc.entity.User;
import com.example.dmc.repository.DataSetRepository;
import com.example.dmc.service.DataSetService;
import com.example.dmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DataSetServiceImpl implements DataSetService {
    private final DataSetRepository dataSetRepository;
    private final UserService userService;

    @Autowired
    public DataSetServiceImpl(DataSetRepository dataSetRepository, UserService userService) {
        this.dataSetRepository = dataSetRepository;
        this.userService = userService;
    }

    @Override
    public DataSet create(DataSet dataSet) {
        User user = userService.getCurrentUser();
        dataSet.setUser(user);
       /* if (algorithmExists(name)) {
            //       LOGGER.error("Not unique name {}", restaurantDto.getName());
            throw new ServiceException("Name should be unique");
        }*/

        return dataSetRepository.save(dataSet);
    }

    @Override
    public DataSet create(MultipartFile dataGetter, MultipartFile dataSplitter, MultipartFile dataSaver, String link) throws IOException {
        User user = userService.getCurrentUser();
        Task dataGetterTask = new Task();
        dataGetterTask.setUser(user);
        dataGetterTask.setFileName(dataGetter
                .getOriginalFilename());
        dataGetterTask.setFile(dataGetter
                .getBytes());

        Task dataSplitterTask = new Task();
        dataSplitterTask.setUser(user);
        dataSplitterTask.setFileName(dataSplitter
                .getOriginalFilename());
        dataSplitterTask.setFile(dataSplitter
                .getBytes());

        Task dataSaverTask = new Task();
        dataSaverTask.setUser(user);
        dataSaverTask.setFileName(dataSaver
                .getOriginalFilename());
        dataSaverTask.setFile(dataSaver
                .getBytes());

        DataSet dataSet = new DataSet();
        dataSet.setLink(link);
        dataSet.setDataGetter(dataGetterTask);
        dataSet.setDataSplitter(dataSplitterTask);
        dataSet.setDataSaver(dataSaverTask);

        return dataSetRepository.save(dataSet);
    }



  /*  @Override
    public boolean dataSetExists(DataSet dataSet) {
        return dataSetRepository.countAllByFileNameAndUserId(dataSet.getLink(), dataSet.getUser()
                                                                                       .getId()) != 0;
    }*/
}
