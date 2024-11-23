package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.LoginDto;
import hu.unideb.inf.notemanager.dto.RegistrationDto;

public interface AuthService {
    void registration(RegistrationDto dto);
    String login(LoginDto dto);
}
