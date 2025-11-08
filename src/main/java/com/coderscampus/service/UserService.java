package com.coderscampus.service;

import com.coderscampus.domain.User;
import com.coderscampus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> findAll () {
        return userRepo.findAll();
    }

}
