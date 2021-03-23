package com.example.listingapp.endpoint;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.User;
import com.example.listingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        if (user.getId() > 0) {
            throw new RuntimeException("Id must be 0");
        }
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable("id") int id) throws ResourceNotFoundException {
        return userService.getById(id);
    }

    @PutMapping("{id}")
    public User update(@RequestBody User user, @PathVariable("id") int id) throws ResourceNotFoundException {
        User userFromDB = userService.getById(id);
        userFromDB.setName(user.getName());
        userFromDB.setSurname(user.getSurname());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setPassword(user.getPassword());
        userFromDB.setRole(user.getRole());

        return userService.addUser(userFromDB);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        if (userService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        } else userService.delete(id);
        return ResponseEntity.ok().build();
    }
}


