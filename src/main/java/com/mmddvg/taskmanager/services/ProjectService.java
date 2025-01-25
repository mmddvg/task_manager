package com.mmddvg.taskmanager.services;

import com.mmddvg.taskmanager.dto.NewProject;
import com.mmddvg.taskmanager.exceptions.NotFoundException;
import com.mmddvg.taskmanager.models.Project;
import com.mmddvg.taskmanager.models.Team;
import com.mmddvg.taskmanager.postgresRepo.ProjectRepository;
import com.mmddvg.taskmanager.postgresRepo.TeamRepo;
import com.mmddvg.taskmanager.postgresRepo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepo userRepo;
    private final TeamRepo teamRepo;

    public Project create(NewProject newProject){
        var userDetails = SecurityContextHolder.getContext().getAuthentication();

        var user = userRepo.findByEmail(userDetails.getName()).orElseThrow(() -> new NotFoundException("user", userDetails.getName()));

        Team team = teamRepo.findById(newProject.teamId()).orElseThrow(() -> new NotFoundException(Team.class.getSimpleName(),newProject.teamId().toString()));

        Project project = new Project(newProject);
        project.setTeam(team);

        return projectRepository.save(project);

    }

    public List<Project> getAllByTeam(Integer teamId){
        Team team = this.teamRepo.findById(teamId).orElseThrow(() -> new NotFoundException(Team.class.getSimpleName(),teamId.toString()));


        return projectRepository.findByTeam(team);

    }

}
