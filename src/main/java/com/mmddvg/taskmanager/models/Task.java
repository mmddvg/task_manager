package com.mmddvg.taskmanager.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tasks",indexes = {
        @Index(name = "idx_project_team", columnList = "project_id")
})
public class Task {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20,nullable = false)
    private String name;


    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    @JsonBackReference
    private Project project;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<SubTask> subtasks;

    public Task() {
    }

    public Set<SubTask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Set<SubTask> subtasks) {
        this.subtasks = subtasks;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
