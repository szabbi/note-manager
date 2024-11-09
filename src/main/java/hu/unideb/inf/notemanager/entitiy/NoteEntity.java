package hu.unideb.inf.notemanager.entitiy;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "notes")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "note_title")
    private String title;

    @Column(name = "note_content")
    private String content;

    @Column(name = "created_at")
    private Date created;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity felhasznalo;

    public NoteEntity(long id, String title, String content, Date created, UserEntity felhasznalo) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.felhasznalo = felhasznalo;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(created, that.created) && Objects.equals(felhasznalo, that.felhasznalo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, created, felhasznalo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NoteEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("content='" + content + "'")
                .add("created=" + created)
                .add("felhasznalo=" + felhasznalo)
                .toString();
    }
}
