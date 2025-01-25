package com.mmddvg.taskmanager.postgresRepo;

import com.mmddvg.taskmanager.models.Team;
import com.mmddvg.taskmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team,Integer> {
    @Query("SELECT t FROM Team t " +
            "WHERE t.owner = :user " +
            "OR :user MEMBER OF t.members")
    List<Team> findByUser(@Param("user") User user);

}
