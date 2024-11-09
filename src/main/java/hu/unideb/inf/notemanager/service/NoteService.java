package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto addNote(NoteDto note);
    NoteDto getNoteById(Long id);
    List<NoteDto> getNotes();
    void deleteNote(Long id);
}
