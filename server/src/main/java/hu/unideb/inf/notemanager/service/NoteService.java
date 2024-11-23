package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto addNote(NoteDto note);
    List<NoteDto> getNotesByUser();
    List<NoteDto> getPublicNotes();
    void deleteNote(Long id);
}
