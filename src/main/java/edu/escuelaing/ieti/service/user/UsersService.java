package edu.escuelaing.ieti.service.user;

import edu.escuelaing.ieti.exception.UserAlreadyExistsException;
import edu.escuelaing.ieti.exception.UserNotFoundException;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.repository.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    User save(User user) throws UserAlreadyExistsException;

    Optional<User> findById(String id) throws UserNotFoundException;

    List<User> all();

    void deleteById(String id) throws UserNotFoundException;

    User update(User user, String userId) throws UserNotFoundException;
}
