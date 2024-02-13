package edu.escuelaing.ieti.service.user;

import edu.escuelaing.ieti.exception.UserAlreadyExistsException;
import edu.escuelaing.ieti.exception.UserNotFoundException;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.repository.user.UserDto;
import edu.escuelaing.ieti.repository.user.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMongoDb implements UsersService{
    private final UserMongoRepository userMongoRepository;

    @Autowired
    public UsersServiceMongoDb(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User save(User user) throws UserAlreadyExistsException {
        if (!userMongoRepository.existsById(user.getId())) {
            return userMongoRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(user.getId());
        }

    }

    @Override
    public Optional<User> findById(String id) throws UserNotFoundException {
        if (userMongoRepository.existsById(id)) {
            return userMongoRepository.findById(id);
        } else {
            throw new UserNotFoundException(id);
        }

    }

    @Override
    public List<User> all() {
        return userMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) throws UserNotFoundException {
        if (userMongoRepository.existsById(id)) {
            userMongoRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User update(User user, String userId) throws UserNotFoundException {
        if (userMongoRepository.existsById(userId)) {
            User userToUpdate = userMongoRepository.findById(userId).get();
            UserDto userUpdated = new UserDto(user.getName(), user.getLastName(), user.getEmail());
            userToUpdate.update(userUpdated);
            return userMongoRepository.save(userToUpdate);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
