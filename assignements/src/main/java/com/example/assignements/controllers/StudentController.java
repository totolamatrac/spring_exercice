package com.example.assignements.controllers;

import com.example.assignements.dto.assignement.AssignementCreateResponseDTO;
import com.example.assignements.dto.student.StudentRequestDTO;
import com.example.assignements.dto.student.StudentResponseDTO;
import com.example.assignements.entity.Assignement;
import com.example.assignements.entity.Student;
import com.example.assignements.services.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/students")
public class StudentController {
    
    private StudentService studentService;

    public StudentController(StudentService stService) {
        this.studentService = stService;
    }

    @PostMapping()
    public StudentResponseDTO create(@RequestBody @Valid StudentRequestDTO student) {
        Student save = this.studentService.createStudent(student);
        return new StudentResponseDTO(save.getId(), save.getName(), save.getEmail());
    }

    @GetMapping()
    public List<StudentResponseDTO> GetAllStudents() {
        List<Student> resp = this.studentService.getAllStudents();
        return resp.stream().map(el -> new StudentResponseDTO(el.getId(), el.getName(), el.getEmail())).toList();
    }
}
