package com.example.assignements.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.assignements.dto.assignement.AssignementCreateReqDTO;
import com.example.assignements.dto.assignement.AssignementCreateResponseDTO;
import com.example.assignements.dto.course.CourseResponseDTO;
import com.example.assignements.dto.student.StudentResponseDTO;
import com.example.assignements.entity.Assignement;
import com.example.assignements.services.AssignementService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/assignements")
public class AssignementController {
    
    private AssignementService assignementService;

    public AssignementController(AssignementService assignementService) {
        this.assignementService = assignementService;
    }


    @PostMapping
    public AssignementCreateResponseDTO create(@RequestBody @Valid AssignementCreateReqDTO assignement) {
        Assignement resp = this.assignementService.createAssignement(assignement);
        return new AssignementCreateResponseDTO(resp.getId(), resp.getTitle(), resp.getDueDate(), new CourseResponseDTO(resp.getCourse().getId(), resp.getCourse().getTitle(), resp.getCourse().getDescription()), new StudentResponseDTO(resp.getStudent().getId(), resp.getStudent().getName(), resp.getStudent().getEmail()));
    }
    
    @GetMapping()
    public List<AssignementCreateResponseDTO> getAllAssignements() {
        List<Assignement> response = this.assignementService.getAllAssignements();
        return response.stream().map(resp -> new AssignementCreateResponseDTO(resp.getId(), resp.getTitle(), resp.getDueDate(), new CourseResponseDTO(resp.getCourse().getId(), resp.getCourse().getTitle(), resp.getCourse().getDescription()), new StudentResponseDTO(resp.getStudent().getId(), resp.getStudent().getName(), resp.getStudent().getEmail()))).toList();
    }

    @PostMapping("/{idStudent}/send")
    public ResponseEntity<AssignementCreateResponseDTO> sendAssignement(@PathVariable long idStudent, @RequestBody long idAssignement) {
        
        try {
            Assignement a = this.assignementService.sendAssignement(idStudent, idAssignement);
            AssignementCreateResponseDTO resp = new AssignementCreateResponseDTO(a.getId(), a.getTitle(), a.getDueDate(), new CourseResponseDTO(a.getCourse().getId(), a.getCourse().getTitle(), a.getCourse().getDescription()), new StudentResponseDTO(a.getStudent().getId(), a.getStudent().getName(), a.getStudent().getEmail()));
            return ResponseEntity.ok(resp);
        } catch (DataIntegrityViolationException | IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("/{idAssignement}/grade")
    public ResponseEntity<AssignementCreateResponseDTO> setGrade(@PathVariable long idAssignement, @RequestBody Float grade) {
        try {
            Assignement resp = this.assignementService.setGrade(idAssignement, grade);
            return ResponseEntity.ok().body(new AssignementCreateResponseDTO(resp.getId(), resp.getTitle(), resp.getDueDate(), new CourseResponseDTO(resp.getCourse().getId(), resp.getCourse().getTitle(), resp.getCourse().getDescription()), new StudentResponseDTO(resp.getStudent().getId(), resp.getStudent().getName(), resp.getStudent().getEmail())));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    

}
