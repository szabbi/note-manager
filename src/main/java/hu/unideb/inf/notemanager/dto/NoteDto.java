package hu.unideb.inf.notemanager.dto;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class NoteDto {
    private long id;
    private String title;
    private String content;
    private Date created;

    public NoteDto(long id, String title, String content, Date created) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
    }

    public NoteDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDto noteDto = (NoteDto) o;
        return id == noteDto.id && Objects.equals(title, noteDto.title) && Objects.equals(content, noteDto.content) && Objects.equals(created, noteDto.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, created);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NoteDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("content='" + content + "'")
                .add("created=" + created)
                .toString();
    }
}
