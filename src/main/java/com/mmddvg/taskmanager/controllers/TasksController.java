package com.mmddvg.taskmanager.controllers;

import com.mmddvg.taskmanager.dto.NewTask;
import com.mmddvg.taskmanager.models.Task;
import com.mmddvg.taskmanager.postgresRepo.TaskRepo;
import com.mmddvg.taskmanager.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class TasksController {

    private static final Logger log = LoggerFactory.getLogger(TasksController.class);
    TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> GetAll(){
        return taskService.GetAll();
    }

//    @PostMapping("/tasks")
//    public Task Create(@RequestBody NewTask arg){
//        return taskService.Create(arg);
//    }
    @GetMapping("/tasks/{id}")
    public Task GetOne(@PathVariable("id") Integer id ){
        return taskService.GetOne(id);
    }

}
