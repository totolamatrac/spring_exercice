package com.example.note_eleves.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="notes")
@Entity
public class Note {
    
    @Id @GeneratedValue
    private long id;
    private float score;
    private String subject;

    @ManyToOne  
    @JoinColumn(name = "student_id")
    private Student student;
    
    public Note() {};
    
    public Note(float value, String subject) {
        this.score = value;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float value) {
        this.score = value;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    
}
