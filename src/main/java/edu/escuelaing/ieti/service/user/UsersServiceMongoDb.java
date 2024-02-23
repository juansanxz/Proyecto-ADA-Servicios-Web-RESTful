package edu.escuelaing.ieti.service.user;

import edu.escuelaing.ieti.exception.UserAlreadyExistsException;
import edu.escuelaing.ieti.exception.UserNotFoundException;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.controller.user.UserDto;
import edu.escuelaing.ieti.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User create(UserDto user) {
        return userMongoRepository.save(new User( user ));
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
    public User findByEmail( String email )
            throws UserNotFoundException
    {
        Optional<User> optionalUser = userMongoRepository.findByEmail( email );
        if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            throw new UserNotFoundException("");
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
    public User update(UserDto user, String userId) throws UserNotFoundException {
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
