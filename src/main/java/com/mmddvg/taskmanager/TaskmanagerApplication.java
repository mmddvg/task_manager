package com.mmddvg.taskmanager;

import com.mmddvg.taskmanager.dto.NewUser;
import com.mmddvg.taskmanager.models.User;
import com.mmddvg.taskmanager.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication()
public class TaskmanagerApplication {


	public static void main(String[] args) {
		 SpringApplication.run(TaskmanagerApplication.class, args);
	}


}


