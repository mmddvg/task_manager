package com.mmddvg.taskmanager.dto;

import com.mmddvg.taskmanager.models.User;

public class UserOutput {
    public Integer id;
    public String name;
    public String email;

    public UserOutput(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
