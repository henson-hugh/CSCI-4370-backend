package com.csci4050.movie.api.user;

import com.csci4050.movie.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        if (userRepository.findByEmail(email) == null) {
            return Optional.empty();
        } else {
            return userRepository.findByEmail(email);
        }
    }

    public Optional<User> saveUser(User user) {

        User newUser = user;
        // encode password
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);
        return Optional.empty();
    }

    public Optional<User> updatePassword(User user) {
        User updateUser = userRepository.findById(user.getUid()).get();
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(updateUser);
        return Optional.empty();
    }

    public Optional<User> editUser(int uid, User user) {
        User updatedUser = user;
        updatedUser.setUid(uid);
        updatedUser.setPassword(userRepository.findById(uid).get().getPassword());
        updatedUser.setEmail(user.getEmail());
        return Optional.of(userRepository.save(updatedUser));

    }
}
