package com.mmddvg.taskmanager.services;

import com.mmddvg.taskmanager.dto.NewTeam;
import com.mmddvg.taskmanager.dto.NewUser;
import com.mmddvg.taskmanager.dto.TeamOutput;
import com.mmddvg.taskmanager.dto.UserOutput;
import com.mmddvg.taskmanager.models.Team;
import com.mmddvg.taskmanager.models.User;
import com.mmddvg.taskmanager.postgresRepo.TeamRepo;
import com.mmddvg.taskmanager.postgresRepo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @Mock
    private TeamRepo teamRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private TeamService teamService;  // Service under test

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the SecurityContextHolder to return the mocked security context
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void createTeam() {

        User owner = new User(new NewUser("efi","blah@exampl.ecom","blah"), "encrypted blah");
        NewTeam newTeam = new NewTeam("MyTeam");
        Team expectedTeam = new Team(newTeam, owner);

        when(userRepo.findByEmail(anyString())).thenReturn(Optional.of(owner));
        when(teamRepo.save(any(Team.class))).thenReturn(expectedTeam);
        when(authentication.getName()).thenReturn("john@example.com");

        Team createdTeam = teamService.createTeam(newTeam);

        assertNotNull(createdTeam);
        assertEquals("MyTeam", createdTeam.getName());
        assertEquals(owner, createdTeam.getOwner());
        verify(teamRepo, times(1)).save(any(Team.class));  // Verify save method is called once
    }

    @Test
    void addMember() {
        User owner = new User(new NewUser("efi","blah@exampl.ecom","blah"), "encrypted blah");
        User newMember = new User(new NewUser("poya","poya@exampl.ecom","blah"), "encrypted blah");
        Team team = new Team(new NewTeam("team name"), owner);
        var members = new ArrayList<User>();
        team.setMembers(members);
        team.setId(1);
        when(teamRepo.findById(1)).thenReturn(Optional.of(team));
        when(userRepo.findById(2)).thenReturn(Optional.of(newMember));

        when(authentication.getName()).thenReturn("john@example.com");
        when(userRepo.findByEmail("john@example.com")).thenReturn(Optional.of(owner));

        members.add(newMember);
        team.setMembers(members);
        when(teamRepo.save(team)).thenReturn(team);
        TeamOutput updatedTeam = teamService.addMember(1, 2);
        assertEquals(updatedTeam.members.getFirst().name , newMember.getName());
        verify(teamRepo, times(1)).save(team);  // Verify save method is called
    }


}