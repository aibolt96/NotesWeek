package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NotesDto;
import com.devmountain.noteApp.entities.Notes;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.NotesRepository;
import com.devmountain.noteApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository notesRepository;

    @Override
    @Transactional
    public void addNote(NotesDto notesDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Notes notes = new Notes(notesDto);
        userOptional.ifPresent(notes::setUser);
        notesRepository.saveAndFlush(notes);
    }

    @Override
    @Transactional
    public void deleteNotesById(Long noteId){
        Optional<Notes> notesOptional = notesRepository.findById(noteId);
        notesOptional.ifPresent(notes -> notesRepository.delete(notes));
    }

    @Override
    @Transactional
    public void updateNotesById(NotesDto notesDto){
        Optional<Notes> notesOptional = notesRepository.findById(notesDto.getId());
        notesOptional.ifPresent(notes -> {notes.setBody(notesDtos.getBody());
        notesRepository.saveAndFlush(notes);
        });
    }

    @Override
    public List<NotesDto> getAllNotesByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Notes> notesList = notesRepository.findAllByUserEquals(userOptional.get());
            return notesList.stream().map(notes -> new NotesDto(notes)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<NotesDto> getNoteById(Long noteId){
        Optional<Notes> notesOptional = notesRepository.findById(noteId);
        if (notesOptional.isPresent()){
            return Optional.of(new NotesDto(notesOptional.get()));
        }
        return Optional.empty();
    }
}
