package com.mmddvg.taskmanager.postgresRepo;

import com.mmddvg.taskmanager.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team,Integer> {
}
