package com.example.note_eleves.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.note_eleves.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{}
