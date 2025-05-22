package com.example.assignements.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Courses")
public class Course {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "course")
    private List<Assignement> assignements;
        
    public Course() {
    }

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
        this.assignements = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Assignement> getAssignements() {
        return this.assignements;
    }

    public void setAssignements(List<Assignement> assignmt) {
        this.assignements = assignmt;
    }

    public void addAssignement(Assignement a) {
        this.assignements.add(a);
    }
}
