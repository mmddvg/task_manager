package com.mmddvg.taskmanager.controllers;


import com.mmddvg.taskmanager.dto.NewProject;
import com.mmddvg.taskmanager.models.Project;
import com.mmddvg.taskmanager.services.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Project create(@RequestBody @Valid NewProject newProject){
        return projectService.create(newProject);
    }

    @GetMapping("/team/{teamId}")
    public List<Project> getAllByTeam(@PathVariable("teamId") Integer teamId){
        return projectService.getAllByTeam(teamId);
    }
}
