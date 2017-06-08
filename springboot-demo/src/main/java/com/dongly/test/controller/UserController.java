package com.dongly.test.controller;

import com.dongly.test.entity.User;
import com.dongly.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // GET /pets/42;q=11;r=22

    @RequestMapping(path = "/pets/{petId}", method = RequestMethod.GET)
    public void findPet(@PathVariable String petId, @MatrixVariable int[] q, @MatrixVariable List<String> r) {

        // petId == 42
        // q == 11
        System.out.println(petId + " --- " + q + " ---- " + r);

    }

}
