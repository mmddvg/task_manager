package com.mmddvg.taskmanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mmddvg.taskmanager.dto.NewTeam;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.*;

@Data
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20 ,nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private User owner;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Fetch(FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<User> members;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Project> projects;


    public Team(){}
    public Team(NewTeam newTeam,User user){
        this.setName(newTeam.name());
        this.setOwner(user);
        this.setMembers(new ArrayList<>());
        this.setProjects(new ArrayList<>());
    }

    public boolean canEdit(User user){
        return Objects.equals(this.getOwner().getId(), user.getId());
    }

}
