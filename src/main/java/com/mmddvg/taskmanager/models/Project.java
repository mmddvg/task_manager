package com.mmddvg.taskmanager.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mmddvg.taskmanager.dto.NewProject;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "projects")
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "team_id",nullable = false)
    @JsonBackReference
    private Team team;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks;

    public Project(NewProject newProject) {
        this.name= newProject.name();
        this.description=newProject.description();
    }
}
