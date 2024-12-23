package hu.unideb.inf.notemanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class LoginDto {

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters")
    private String password;

    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDto loginDto = (LoginDto) o;
        return Objects.equals(email, loginDto.email) && Objects.equals(password, loginDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
