package hu.unideb.inf.notemanager.entitiy;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    @NotBlank(message = "Username can't be empty")
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
    private String name;

    @Column(name = "user_email", unique = true)
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email must be valid")
    private String email;

    @Column(name = "user_password")
    @NotBlank(message = "Password can't be empty")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters")
    private String password;

    @OneToMany(mappedBy = "felhasznalo", fetch = FetchType.EAGER)
    private List<NoteEntity> note;

    public UserEntity(long id, String name, String email, String password, List<NoteEntity> note) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.note = note;
    }

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<NoteEntity> getNote() {
        return note;
    }

    public void setNote(List<NoteEntity> note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, note);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("note=" + note)
                .toString();
    }
}
