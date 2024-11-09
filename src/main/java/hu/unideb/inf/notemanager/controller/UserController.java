package hu.unideb.inf.notemanager.controller;

import hu.unideb.inf.notemanager.dto.UserDto;
import hu.unideb.inf.notemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public UserDto addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @GetMapping("/getbyid")
    public UserDto getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getall")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @PutMapping("/update")
    public UserDto updateUser(@RequestBody UserDto user) {
        if (user.getId() > 0L) {
            return userService.addUser(user);
        }
        return null;
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}

