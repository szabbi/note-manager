package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.NoteDto;
import hu.unideb.inf.notemanager.entitiy.NoteEntity;
import hu.unideb.inf.notemanager.mapper.NoteEntityMapper;
import hu.unideb.inf.notemanager.repository.NoteRepository;
import hu.unideb.inf.notemanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteEntityMapper noteEntityMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public NoteDto addNote(NoteDto note) {
        NoteEntity noteEntity = noteEntityMapper.toEntity(note);

        String user =  SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(noteEntity.getPublicNote() + " after mapping");
        noteEntity.setFelhasznalo(userRepository.findByEmail(user));
        noteEntity.setCreatedAt(LocalDateTime.now());
        noteEntity = noteRepository.save(noteEntity);

        NoteDto dto = new NoteDto();
        dto = noteEntityMapper.toDtoWithUsername(noteEntity);

        return dto;
    }

    @Override
    public List<NoteDto> getNotesByUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<NoteEntity> entities = noteRepository.findAllByFelhasznaloEmail(userEmail);

        return noteEntityMapper.toDtoListWithUsername(entities);
    }

    @Override
    public List<NoteDto> getPublicNotes() {
        List<NoteEntity> entites = noteRepository.findAllByPublicNoteTrue();
        List<NoteDto> dtos = new ArrayList<>();

        dtos = noteEntityMapper.toDtoListWithUsername(entites);
        return dtos;
    }

    @Override
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new EntityNotFoundException("Note with ID " + id + " does not exist.");
        }
        noteRepository.deleteById(id);
    }
}
