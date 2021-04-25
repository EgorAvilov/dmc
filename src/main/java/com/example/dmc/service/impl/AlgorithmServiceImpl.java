package com.example.dmc.service.impl;

import com.example.dmc.entity.Algorithm;
import com.example.dmc.entity.Task;
import com.example.dmc.entity.Type;
import com.example.dmc.entity.User;
import com.example.dmc.exception.ServiceException;
import com.example.dmc.repository.AlgorithmRepository;
import com.example.dmc.repository.TaskRepository;
import com.example.dmc.service.AlgorithmService;
import com.example.dmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    private final AlgorithmRepository algorithmRepository;
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public AlgorithmServiceImpl(AlgorithmRepository algorithmRepository, TaskRepository taskRepository, UserService userService) {
        this.algorithmRepository = algorithmRepository;
        this.taskRepository = taskRepository;
        this.userService = userService;
    }


    @Override
    public Algorithm create(List<MultipartFile> files, List<String> inputType, List<String> outputType, String name) throws IOException {
        Algorithm algorithm = new Algorithm();
        User user = userService.getCurrentUser();
        algorithm.setUser(user);

        if (algorithmExists(algorithm)) {
            throw new ServiceException("Name should be unique");
        }
        for (MultipartFile file : files) {
            Path filepath = Paths.get("src/main/resources/algorithms", file.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(filepath)) {
                os.write(file.getBytes());
            }
        }
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            Task task = new Task();
            task.setFileName(files.get(i)
                                  .getOriginalFilename());
            task.setInputType(Type.valueOf(inputType.get(i)));
            task.setOutputType(Type.valueOf(outputType.get(i)));
            tasks.add(task);
        }

        algorithm.setName(name);
        algorithm.setTasks(tasks);
        return algorithmRepository.save(algorithm);
    }

    @Override
    public Algorithm create(Algorithm algorithm) {
        User user = userService.getCurrentUser();
        algorithm.setUser(user);
        if (algorithmExists(algorithm)) {
            throw new ServiceException("повтор алгоиртма по имени");
        }
        List<Task> tasks = algorithm.getTasks()
                                    .stream()
                                    .map(Task::getId)
                                    .map((taskId) -> taskRepository.findById(taskId)
                                                                   .get())
                                    .collect(Collectors.toList());
        algorithm.setTasks(tasks);
        return algorithmRepository.saveAndFlush(algorithm);
    }

    @Override
    public boolean algorithmExists(Algorithm algorithm) {
        return algorithmRepository.countAllByNameAndUserId(algorithm.getName(), algorithm.getUser()
                                                                                         .getId()) != 0;
    }
}
