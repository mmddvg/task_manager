package com.mmddvg.taskmanager.dto;

import com.mmddvg.taskmanager.models.Team;
import com.mmddvg.taskmanager.models.User;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeamOutputTest {

    @Test
    public void shouldMapTeamOutput(){

        User owner = new User();
        owner.setId(1);
        owner.setName("nazanin");
        owner.setEmail("blah@example.com");

        Team team = getTeam(owner);

        TeamOutput teamOutput = new TeamOutput(team);

        assertEquals(team.getId(),teamOutput.id);
        assertEquals(team.getName(),teamOutput.name);
        assertEquals(team.getOwner().getId(),teamOutput.ownerId);


        var members = team.getMembers().toArray(new User[0]);
        var membersOutput = teamOutput.members.toArray(new UserOutput[0] );
        for (int i = 0;i < members.length;i++){
            var mem = members[i];
            var output = membersOutput[i];
            assertEquals(mem.getId(),output.id);
            assertEquals(mem.getEmail(),output.email);
            assertEquals(mem.getName(),output.name);
        }
    }

    private static @NotNull Team getTeam(User owner) {
        Team team = new Team();
        team.setName("gotcha");
        team.setId(10);
        team.setOwner(owner);

        User mem1 = new User();
        mem1.setId(2);
        mem1.setName("mil");
        mem1.setEmail("mil@example.com");

        User mem2 = new User();
        mem2.setId(3);
        mem2.setName("efi");
        mem2.setEmail("efi@example.com");

        Set<User> members = new HashSet<User>();
        members.add(mem1);
        members.add(mem2);

        team.setMembers(members);
        return team;
    }
}