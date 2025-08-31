package com.project.quiz.service;

import com.project.quiz.model.User;
import com.project.quiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepo.findById(id);
    }

    public User updateUser(Integer id, User updatedUser) {
        return userRepo.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setGender(updatedUser.getGender());
            user.setRole(updatedUser.getRole());
            return userRepo.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
}
