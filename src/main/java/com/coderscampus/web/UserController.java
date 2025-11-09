package com.coderscampus.web;

import com.coderscampus.domain.User;
import com.coderscampus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers (ModelMap model) {
        List<User> users = userService.findAll();
        model.put("users", users);
        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser (ModelMap model, @PathVariable Long userId) {
        userService.findOne(userId);
        model.put("users", Arrays.asList(user));
        return "users";
    }
}