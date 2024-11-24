package hu.unideb.inf.notemanager.controller;

import hu.unideb.inf.notemanager.dto.NoteDto;
import hu.unideb.inf.notemanager.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @PostMapping("/add")
    public NoteDto addNote(@RequestBody NoteDto note){
        return noteService.addNote(note);
    }

    @GetMapping("/getbyuser")
    public ResponseEntity<List<NoteDto>> getNotesByUser() {
        return ResponseEntity.ok(noteService.getNotesByUser());
    }

    @GetMapping("/getpublicnotes")
    public List<NoteDto> getPublicNotes() {
        return noteService.getPublicNotes();
    }

    @PutMapping("/update")
    public NoteDto updateNote(@RequestBody NoteDto note) {
        if (note.getId() > 0L) {
            return noteService.addNote(note);
        }
        return null;
    }


    @DeleteMapping("/delete")
    public void deleteNote(@RequestParam Long id) {
        noteService.deleteNote(id);
    }

}
