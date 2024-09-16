package com.mmddvg.taskmanager.dto;

import com.mmddvg.taskmanager.models.Team;
import com.mmddvg.taskmanager.models.User;

import java.util.ArrayList;
import java.util.List;

public class TeamOutput {
    public Integer id ;
    public String name;
    public Integer ownerId;
    public List<UserOutput> members ;

    public TeamOutput(Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.ownerId = team.getOwner().getId();
        this.members = new ArrayList<>();
        for (User member : team.getMembers()){
            this.members.add(new UserOutput(member));
        }
    }
}
