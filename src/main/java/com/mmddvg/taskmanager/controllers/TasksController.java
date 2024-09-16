package com.mmddvg.taskmanager.controllers;

import com.mmddvg.taskmanager.dto.NewTask;
import com.mmddvg.taskmanager.models.Task;
import com.mmddvg.taskmanager.services.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/tasks")
public class TasksController {

    private static final Logger log = LoggerFactory.getLogger(TasksController.class);
    TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<Task> GetAll(){
        return taskService.GetAll();
    }

    @PostMapping("")
    public Task Create(@Valid @RequestBody NewTask arg){
        return taskService.Create(arg);
    }
    @GetMapping("/{id}")
    public Task GetOne(@PathVariable("id") Integer id ){
        return taskService.GetOne(id);
    }

}
