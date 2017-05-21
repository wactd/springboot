package com.dongly.test.controller;

import com.dongly.test.entity.User;
import com.dongly.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tiger on 17-5-22.
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/test/users")
    public List<User> users() {
        return userRepository.findAll();
    }

}
