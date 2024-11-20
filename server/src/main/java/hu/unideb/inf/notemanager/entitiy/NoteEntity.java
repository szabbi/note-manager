package hu.unideb.inf.notemanager.entitiy;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "notes")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "note_title")
    @NotBlank(message = "Title can't be empty")
    @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
    private String title;

    @Column(name = "note_content")
    @NotBlank(message = "Content can't be empty")
    @Size(min = 5, max = 500, message = "Content must be between 5 and 500 characters")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity felhasznalo;

    public NoteEntity(long id, String title, String content, LocalDateTime createdAt, UserEntity felhasznalo) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.felhasznalo = felhasznalo;
    }

    public NoteEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime created) {
        this.createdAt = created;
    }

    public UserEntity getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(UserEntity felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteEntity that = (NoteEntity) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(felhasznalo, that.felhasznalo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createdAt, felhasznalo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NoteEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("content='" + content + "'")
                .add("created=" + createdAt)
                .add("felhasznalo=" + felhasznalo)
                .toString();
    }
}
