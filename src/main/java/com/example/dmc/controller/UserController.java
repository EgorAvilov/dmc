package com.example.dmc.controller;

import com.example.dmc.entity.User;
import com.example.dmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@Controller
@RequestMapping("/sign-up-page")
public class UserController {

    private static final String INDEX_TEMPLATE_NAME = "index";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public String create(@RequestBody User user) {
        userService.create(user);
        return INDEX_TEMPLATE_NAME;
        //return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }
}
*/
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }
}