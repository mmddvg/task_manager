package com.mmddvg.taskmanager.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mmddvg.taskmanager.dto.NewUser;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    @JsonManagedReference
    private List<Team> OwnedTeams;

    @ManyToMany(mappedBy = "members")
    private Set<Team> teams;

    public User(){

    }

    public User(NewUser tmp,String password){
        this.name = tmp.name();
        this.email = tmp.email();
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getOwnedTeams() {
        return OwnedTeams;
    }

    public void setOwnedTeams(List<Team> ownedTeams) {
        OwnedTeams = ownedTeams;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
