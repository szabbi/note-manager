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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();

        noteEntity.setFelhasznalo(userRepository.findByEmail(user));
        noteEntity.setCreatedAt(LocalDateTime.now());
        noteEntity = noteRepository.save(noteEntity);

        NoteDto dto = new NoteDto();
        dto = noteEntityMapper.toDtoWithUsername(noteEntity);

        return dto;
    }

    @Override
    public NoteDto getNoteById(Long id) {
        NoteEntity entity = noteRepository.getReferenceById(id);
        NoteDto dto = new NoteDto();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCreatedBy(entity.getFelhasznalo().getName());

        return dto;
    }

    @Override
    public List<NoteDto> getNotes() {
        List<NoteEntity> entites = noteRepository.findAll();
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
