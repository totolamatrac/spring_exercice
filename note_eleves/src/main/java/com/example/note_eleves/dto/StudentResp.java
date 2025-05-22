package com.example.note_eleves.dto;

import java.util.List;

import com.example.note_eleves.entity.Note;

public record StudentResp(long id, String name, List<NoteResp> notes) { }
