package hu.unideb.inf.notemanager.repository;

import hu.unideb.inf.notemanager.entitiy.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
