package com.example.note_eleves.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.note_eleves.dto.NoteReq;
import com.example.note_eleves.dto.NoteResp;
import com.example.note_eleves.dto.StudentReq;
import com.example.note_eleves.dto.StudentResp;
import com.example.note_eleves.entity.Note;
import com.example.note_eleves.entity.Student;
import com.example.note_eleves.repository.NoteRepository;
import com.example.note_eleves.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentRepository studentRepo;
    private NoteRepository noteRepo;

    public StudentController(StudentRepository studentR, NoteRepository noteR) {
        this.studentRepo = studentR;
        this.noteRepo = noteR;
    }

    @PostMapping()
    public StudentResp create(@RequestBody StudentReq student) {
        this.studentRepo.findByName(student.name()).ifPresent(existing -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User existing");
        });

        Student save = this.studentRepo.save(new Student(student.name()));
        return new StudentResp(save.getId(), save.getName(), this.parseNotes(save.getNotes()));
    }

    private List<NoteResp> parseNotes(List<Note> n) {
        return n.stream()
                .map(note -> new NoteResp(note.getId(), note.getScore(), note.getSubject())).toList();
    }

    @GetMapping()
    public List<StudentResp> getAllStudents() {
        return this.studentRepo.findAll().stream()
                .map(student -> new StudentResp(student.getId(), student.getName(),
                        this.parseNotes(student.getNotes())))
                .toList();
    }

    @GetMapping("/{id}/notes")
    public List<NoteResp> getStudentNotes(@PathVariable long id) {
        Student resp = this.studentRepo.findById(id).orElseThrow();
        return this.parseNotes(resp.getNotes());
    }

    @GetMapping("/{id}/average")
    public float getStudentAverage(@PathVariable long id) {
        Student student = this.studentRepo.findById(id).orElseThrow();
        if (!student.getNotes().isEmpty()) {
            float sumNotes = 0;
            for (Note n : student.getNotes()) {
                sumNotes += n.getScore();
            }
            return sumNotes / student.getNotes().size();
        } else {
            return 0;
        }
    }

    @GetMapping("/{id}")
    public List<NoteResp> getStudentNoteBySubject(@PathVariable long id, @RequestParam(name = "subject") String suject) {
        Student student = this.studentRepo.findById(id).orElseThrow();
        List<Note> resp = student.getNotes().stream()
            .filter(note -> note.getSubject().equals(suject))
            .toList();
        
        return this.parseNotes(resp);
    }
    

    @PutMapping("/{id}")
    public StudentResp addNote(@PathVariable long id, @RequestBody NoteReq note) {
        Student s = this.studentRepo.findById(id).orElseThrow();
        Note n = new Note(note.score(), note.subject());
        s.addNote(n);

        Student save = this.studentRepo.save(s);

        return new StudentResp(save.getId(), save.getName(), this.parseNotes(save.getNotes()));
    }

}
