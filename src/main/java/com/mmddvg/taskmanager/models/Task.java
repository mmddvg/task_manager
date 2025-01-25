package com.mmddvg.taskmanager.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mmddvg.taskmanager.dto.NewTask;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tasks",indexes = {
        @Index(name = "idx_project_team", columnList = "project_id")
})
@NoArgsConstructor
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
    private List<SubTask> subtasks;

    public Task(NewTask arg){
        this.name = arg.name();
        this.description = arg.description();
        var tmp = new Project();
        tmp.setId(arg.project_id());
        this.project = tmp;
    }

}
