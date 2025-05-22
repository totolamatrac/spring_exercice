package com.example.assignements.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.assignements.dto.assignement.AssignementCreateReqDTO;
import com.example.assignements.dto.assignement.AssignementCreateResponseDTO;
import com.example.assignements.entity.Assignement;
import com.example.assignements.entity.Course;
import com.example.assignements.entity.Student;
import com.example.assignements.repository.AssignementRepository;
import com.example.assignements.repository.CourseRepository;
import com.example.assignements.repository.StudentRepository;

@Service
public class AssignementService {
    private AssignementRepository assignementRepo;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public AssignementService(AssignementRepository assignementRepo, StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.assignementRepo = assignementRepo;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Assignement createAssignement(AssignementCreateReqDTO assignement) {
        Student s = this.studentRepository.findById(assignement.idStudent()).orElseThrow();
        Course c = this.courseRepository.findById(assignement.idCourse()).orElseThrow();

        return this.assignementRepo.save(new Assignement(assignement.title(), assignement.dueDate(), c, s));
    }

    public List<Assignement> getAllAssignements() {
        return this.assignementRepo.findAll();
    }

     public Assignement sendAssignement(long idStudent, long idAssignement) {
        Student s = this.studentRepository.findById(idStudent).orElseThrow();
        Assignement a = this.assignementRepo.findById(idAssignement).orElseThrow();

        if (s.getId() != a.getStudent().getId()) {
            throw new DataIntegrityViolationException("Pas le bon user");
        }
        if (a.getGrade() != null) {
            throw new IllegalStateException("Devoir déja envoyé!");
        }
        if (LocalDateTime.now().isAfter(a.getDueDate())) {
            throw new IllegalArgumentException("Trop tard!");
        } else {
            a.setSubmittedAt(LocalDateTime.now());
        }

        return this.assignementRepo.save(a);
    }

    public Assignement setGrade(long idAssignement, Float grade) {
        Assignement a = this.assignementRepo.findById(idAssignement).orElseThrow();
        if (grade == null || grade < 0 || grade > 20) {
            throw new IllegalArgumentException("grade is incorect");
        } 
        a.setGrade(grade);
        return this.assignementRepo.save(a);
    }
}
