package com.example.task_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

public interface TaskRepository extends JpaRepository<com.example.task_project.data.Task, Long> {};
