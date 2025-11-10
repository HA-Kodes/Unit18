package com.coderscampus.service;

import com.coderscampus.domain.User;
import com.coderscampus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> findByNameAndUsername(String name, String username) {
        return userRepo.findByNameAndUsername(name, username);
    }

    public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
        return userRepo.findByCreatedDateBetween(date1, date1);
    }


    public List<User> findAll () {
        return userRepo.findAll();
    }

    public User findById(Long userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        return userOpt.orElse(new User());

    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);

    }
}
