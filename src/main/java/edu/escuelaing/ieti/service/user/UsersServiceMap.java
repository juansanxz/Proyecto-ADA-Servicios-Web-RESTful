package edu.escuelaing.ieti.service.user;

import edu.escuelaing.ieti.exception.UserAlreadyExistsException;
import edu.escuelaing.ieti.exception.UserNotFoundException;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.repository.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMap implements UsersService{
    private final HashMap<String, User> users = new HashMap<String, User>();

    @Override
    public User save(User user) throws UserAlreadyExistsException {
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return users.get(user.getId());
        } else {
            throw new UserAlreadyExistsException(user.getId());
        }

    }

    @Override
    public Optional<User> findById(String id) throws UserNotFoundException {
        if (users.containsKey(id)) {
            User user = users.get(id);
            return Optional.ofNullable(user);
        } else {
            throw new UserNotFoundException(id);
        }

    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(String id) throws UserNotFoundException {
        if (users.containsKey(id)) {
            users.remove(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User update(UserDto user, String userId) throws UserNotFoundException {
        if (users.containsKey(userId)) {
            User userToUpdate = users.get(userId);
            userToUpdate.update(user);
            return users.get(userId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
