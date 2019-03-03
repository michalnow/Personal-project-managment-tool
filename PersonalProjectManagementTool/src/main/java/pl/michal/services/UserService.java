package pl.michal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.michal.entities.User;
import pl.michal.exceptions.UsernameAlreadyExistsException;
import pl.michal.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique(exception)
            newUser.setUsername(newUser.getUsername());
            //make sure that password and confirmPassword match

            //we dont persist or show the confirmPassword

            return userRepository.save(newUser);

        }catch(Exception ex){
            throw new UsernameAlreadyExistsException("Username " + newUser.getUsername() + " already exists");
        }


    }
}
