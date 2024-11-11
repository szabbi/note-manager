package hu.unideb.inf.notemanager.service;

import hu.unideb.inf.notemanager.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUser();
    void deleteUser(Long id);

}
