package com.devmountain.noteApp.controllers;

import com.devmountain.noteApp.dtos.NotesDto;
import com.devmountain.noteApp.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @GetMapping("/user/{userId}")
    public List<NotesDto> getNotesByUser(@PathVariable Long userId){
        return notesService.getAllNotesByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public void addNote(@RequestBody NotesDto notesDto, @PathVariable Long userId){
        notesService.addNote(notesDto, userId);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNoteById(@PathVariable Long noteId){
        notesService.deleteNotesById(noteId);
    }

    @PutMapping
    public void updateNote(@RequestBody NotesDto notesDto){
        notesService.updateNotesById(notesDto);
    }

    @GetMapping("/{noteId}")
    public Optional<NotesDto> getNoteById(@PathVariable Long noteId){
        return notesService.getNoteById(noteId);
    }
}
