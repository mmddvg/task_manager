package com.mmddvg.taskmanager.controllers;

import com.mmddvg.taskmanager.dto.NewUser;
import com.mmddvg.taskmanager.dto.SignupOutput;
import com.mmddvg.taskmanager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupOutput> signUp(@Valid @RequestBody NewUser body){
        return ResponseEntity.ok(this.userService.signUp(body));
    }

}
