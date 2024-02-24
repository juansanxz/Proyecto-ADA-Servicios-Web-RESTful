package edu.escuelaing.ieti.controller.user;


import edu.escuelaing.ieti.exception.UserNotFoundException;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(usersService.create(userDto));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        Optional<User> user = usersService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(user.get());


    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDto userUpdate) {
        Optional<User> user = usersService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        User userUpdated = usersService.update(userUpdate, id);
        return ResponseEntity.ok(userUpdated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        Optional<User> user = usersService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        usersService.deleteById(id);
        return ResponseEntity.ok().build();

    }
}

