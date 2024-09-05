package com.mmddvg.taskmanager.postgresRepo;

import com.mmddvg.taskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {

}
