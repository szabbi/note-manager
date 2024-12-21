package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService getUserDetailsService();
    String getCurrentUsername();
}
