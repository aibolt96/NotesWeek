package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NotesDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface NotesService {
    @Transactional
    void addNote(NotesDto notesDto, Long userId);

    @Transactional
    void deleteNotesById(Long noteId);

    @Transactional
    void updateNotesById(NotesDto notesDto);

    List<NotesDto> getAllNotesByUserId(Long userId);

    Optional<NotesDto> getNoteById(Long noteId);
}
