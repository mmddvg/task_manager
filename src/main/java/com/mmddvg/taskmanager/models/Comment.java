package com.mmddvg.taskmanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subtask_id", nullable = false)
    @JsonBackReference
    private SubTask subtask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replied_to")
    @JsonBackReference
    private Comment repliedTo;

    @OneToMany(mappedBy = "repliedTo", cascade = CascadeType.ALL)  // Use "repliedTo" field name here
    @JsonManagedReference
    private Set<Comment> replies;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Time createdAt;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubTask getSubtask() {
        return subtask;
    }

    public void setSubtask(SubTask subtask) {
        this.subtask = subtask;
    }

    public Set<Comment> getReplies() {
        return replies;
    }

    public void setReplies(Set<Comment> replies) {
        this.replies = replies;
    }

    public Comment getRepliedTo() {
        return repliedTo;
    }

    public void setRepliedTo(Comment repliedTo) {
        this.repliedTo = repliedTo;
    }

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }
}
