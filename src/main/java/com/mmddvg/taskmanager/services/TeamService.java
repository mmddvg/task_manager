package com.mmddvg.taskmanager.services;


import com.mmddvg.taskmanager.dto.NewTeam;
import com.mmddvg.taskmanager.dto.TeamOutput;
import com.mmddvg.taskmanager.exceptions.NotAuthorizedExecption;
import com.mmddvg.taskmanager.exceptions.NotFoundException;
import com.mmddvg.taskmanager.models.Team;
import com.mmddvg.taskmanager.models.User;
import com.mmddvg.taskmanager.postgresRepo.TeamRepo;
import com.mmddvg.taskmanager.postgresRepo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
public class TeamService {
    private static final Logger log = LoggerFactory.getLogger(TeamService.class);
    private final TeamRepo teamRepo;
    private final UserRepo userRepo;

    public TeamService(TeamRepo teamRepo, UserRepo userRepo) {
        this.teamRepo = teamRepo;
        this.userRepo = userRepo;
    }

    public Team createTeam(NewTeam newTeam){
        var userDetails = SecurityContextHolder.getContext().getAuthentication();

        var user = userRepo.findByEmail(userDetails.getName()).orElseThrow(() -> new NotFoundException("user",userDetails.getName()));

        Team team = new Team(newTeam,user);

        return teamRepo.save(team);
    }

    public TeamOutput addMember(Integer teamId, Integer userId){
        Team team = teamRepo.findById(teamId).orElseThrow(() -> new NotFoundException("team",teamId.longValue()));

        var userDetails = SecurityContextHolder.getContext().getAuthentication();
        User ownerUser = userRepo.findByEmail(userDetails.getName()).orElseThrow(() -> new NotFoundException("user",userDetails.getName()));

        if (!team.canEdit(ownerUser)){
            throw new NotAuthorizedExecption("not authorized to add members to this team");
        }

        User newMember = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user",userId.longValue()));
        team.getMembers().add(newMember);

        team = teamRepo.save(team);



        return new TeamOutput(team);
    }

    public TeamOutput removeMember(Integer teamId , Integer userId){
        Team team = teamRepo.findById(teamId).orElseThrow(() -> new NotFoundException("team",teamId.longValue()));

        var userDetails = SecurityContextHolder.getContext().getAuthentication();
        User ownerUser = userRepo.findByEmail(userDetails.getName()).orElseThrow(() -> new NotFoundException("user",userDetails.getName()));

        if (!team.canEdit(ownerUser)){
            throw new NotAuthorizedExecption("not authorized to remove members from this team");
        }

        User member = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user",userId.longValue()));
        team.getMembers().remove(member);

        return new TeamOutput(teamRepo.save(team));
    }
}
