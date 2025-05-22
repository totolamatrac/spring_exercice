package com.example.task_project.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.task_project.DTO.TaskRequestDto;
import com.example.task_project.DTO.TaskResponseDto;
import com.example.task_project.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController()
@RequestMapping("/task")
public class TaskController {
    
    private TaskRepository taskRepo;

    public TaskController(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    @PostMapping()
    public TaskResponseDto addTask(@RequestBody TaskRequestDto req) {
        com.example.task_project.data.Task task = new com.example.task_project.data.Task();
        task.setTitle(req.title());
        task.setDescription(req.description());
        task.setCompleted(false);
        
        com.example.task_project.data.Task save = this.taskRepo.save(task);
        return new TaskResponseDto(save.getId(), save.getTitle(), save.getDescription(), save.isCompleted());
    }
    
    @PutMapping("/{id}/complete")
    public TaskResponseDto completeTask(@PathVariable long id) {
        
        com.example.task_project.data.Task t = this.taskRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objet introuvable"));
       
        t.setCompleted(true);
        com.example.task_project.data.Task save = this.taskRepo.save(t);
        return new TaskResponseDto(save.getId(), save.getTitle(), save.getDescription(), save.isCompleted());
        
    }

    @GetMapping()
    public List<TaskResponseDto> getAll() {
        return this.taskRepo.findAll().stream()
            .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted()))
            .toList();
    }
}
