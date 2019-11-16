package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;


@RestController
public class MainController {

    private UserDao userDao;

    public MainController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/")
    public String function(@RequestBody User user) {
        return Integer.toString(userDao.addUser(user));
    }

    @PostMapping("/id")
    public User getById(@RequestBody User user) {
        if (isNull(user.getId())) return null;

        List<User> users = userDao.getUser(user.getId());
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    @GetMapping("/users")
    public List<User> users(Model model) {
        model.getAttribute("asd");
        return userDao.getAllUsers();
    }


}
