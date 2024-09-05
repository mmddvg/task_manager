package com.mmddvg.taskmanager.services;


import com.mmddvg.taskmanager.dto.NewTask;
import com.mmddvg.taskmanager.models.Task;
import com.mmddvg.taskmanager.postgresRepo.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    TaskRepo taskRepo;


//    public Task Create(NewTask arg){
//        return taskRepo.save( new Task(arg.name(),arg.project_id()));
//    }

    public Task GetOne(Integer id){
        return taskRepo.findById(id).orElse(null);
    }

    public List<Task> GetAll(){
        return taskRepo.findAll();
    }

}
