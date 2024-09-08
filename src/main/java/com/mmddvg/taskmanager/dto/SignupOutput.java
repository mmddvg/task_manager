package com.mmddvg.taskmanager.dto;

import com.mmddvg.taskmanager.models.User;

public class SignupOutput{
    Integer id;
    String name;
    String email;
    String token;

    public SignupOutput(User user, String token) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.token = token;
    }
}