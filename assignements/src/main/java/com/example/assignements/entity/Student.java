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
@Table(name = "Students")
public class Student {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "student")
    private List<Assignement> assignments;

    public Student() {}

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
        this.assignments = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Assignement> getAssignements() {
        return this.assignments;
    }

    public void setAssignements(List<Assignement> assigmts) {
        this.assignments = assigmts;
    }

    public void addAssignement(Assignement a) {
        this.assignments.add(a);
    }
}
