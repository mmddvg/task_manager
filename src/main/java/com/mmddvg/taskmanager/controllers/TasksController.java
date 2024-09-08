package com.mmddvg.taskmanager.controllers;

import com.mmddvg.taskmanager.dto.NewTask;
import com.mmddvg.taskmanager.models.Task;
import com.mmddvg.taskmanager.postgresRepo.TaskRepo;
import com.mmddvg.taskmanager.services.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException arg){
        var errors = new HashMap<String,String>();

        arg.getBindingResult().getAllErrors().forEach(err -> {
            var fieldName = ((FieldError) err).getField();
            errors.put(fieldName,err.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
