package com.mmddvg.taskmanager.services;


import com.mmddvg.taskmanager.dto.NewTask;
import com.mmddvg.taskmanager.models.Task;
import com.mmddvg.taskmanager.postgresRepo.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }



    public Task Create(NewTask arg){
        return taskRepo.save(new Task(arg));
    }

    public Task GetOne(Integer id){
        return taskRepo.findById(id).orElse(null);
    }

    public List<Task> GetAll(){
        return taskRepo.findAll();
    }

}
