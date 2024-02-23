package edu.escuelaing.ieti.service.user;

import edu.escuelaing.ieti.controller.user.UserDto;
import edu.escuelaing.ieti.exception.UserAlreadyExistsException;
import edu.escuelaing.ieti.exception.UserNotFoundException;
import edu.escuelaing.ieti.repository.user.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    User create(UserDto user);

    Optional<User> findById(String id) throws UserNotFoundException;
    User findByEmail( String email )
            throws UserNotFoundException;

    List<User> all();

    void deleteById(String id) throws UserNotFoundException;

    User update(UserDto user, String userId) throws UserNotFoundException;
}
