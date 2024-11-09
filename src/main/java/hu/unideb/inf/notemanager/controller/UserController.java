package hu.unideb.inf.notemanager.controller;

import hu.unideb.inf.notemanager.dto.UserDto;
import hu.unideb.inf.notemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello maki!";
    }

    @PostMapping("/saveuser")
    public UserDto addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @GetMapping("/getuserbyid")
    public UserDto getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getusers")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @PutMapping("/updateuser")
    public UserDto updateUser(@RequestBody UserDto user) {
        if (user.getId() > 0L) {
            return userService.addUser(user);
        }
        return null;
    }

    @DeleteMapping("/deleteuser")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}

