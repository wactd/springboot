package com.dongly.test.controller;

import com.dongly.test.entity.User;
import com.dongly.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * dd
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/test/user/{userId}")
    public User selectById(@PathVariable Integer userId) {
        return userService.selectById(userId);
    }

    @GetMapping(value = "/test/user/list")
    public Object selectAll() {
        return userService.selectAll();
    }

}
