package com.mmddvg.taskmanager.services;


import com.mmddvg.taskmanager.dto.LoginRequest;
import com.mmddvg.taskmanager.dto.NewUser;
import com.mmddvg.taskmanager.dto.SignupOutput;
import com.mmddvg.taskmanager.models.User;
import com.mmddvg.taskmanager.postgresRepo.UserRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtService jwtService;

    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepo, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }


    public SignupOutput signUp(NewUser body){
        User user = new User(body,passwordEncoder.encode(body.password()));
        user = userRepo.save(user);
        String token = jwtService.generateJwt(new HashMap<>(),user);

        return new SignupOutput(user,token);
    }

    public SignupOutput logIn(LoginRequest arg)throws Exception{
        User user = userRepo.findByEmail(arg.email()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        boolean match = passwordEncoder.matches(arg.password(), user.getPassword());
        if (!match){
            throw new BadRequestException("wrong credentials");
        }

        String token = this.jwtService.generateJwt(new HashMap<>(),user);

        return new SignupOutput(user,token);
    }

    public User getUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByEmail(auth.getName()).orElse(null);
    }
}
