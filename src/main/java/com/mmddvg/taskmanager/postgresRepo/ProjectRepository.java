package com.mmddvg.taskmanager.postgresRepo;

import com.mmddvg.taskmanager.models.Project;
import com.mmddvg.taskmanager.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {


    @Query("SELECT p FROM Project p WHERE p.team = :team ")
    List<Project> findByTeam(@Param("team") Team team);
}