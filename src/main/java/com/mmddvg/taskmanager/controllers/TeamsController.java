package com.mmddvg.taskmanager.controllers;


import com.mmddvg.taskmanager.dto.NewTeam;
import com.mmddvg.taskmanager.models.Team;
import com.mmddvg.taskmanager.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teams")
public class TeamsController {
    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("")
    public Team createTeam(@Valid @RequestBody NewTeam newTeam){
        return teamService.createTeam(newTeam);
    }


    @PatchMapping("{id}/add/{user_id}")
    public Team addMember(@PathVariable("id") Integer teamId,@PathVariable("user_id") Integer userId){
        return this.teamService.addMember(teamId,userId);
    }

    @DeleteMapping("{id}/remove/{user_id}")
    public Team removeMember(@PathVariable("id") Integer teamId , @PathVariable("user_id") Integer userId){
        return this.teamService.removeMember(teamId,userId);
    }
}
