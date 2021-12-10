package com.example.springdatajdbcdemo.controllers;

import com.example.springdatajdbcdemo.models.UserOptimistic;
import com.example.springdatajdbcdemo.models.UserPessimistic;
import com.example.springdatajdbcdemo.services.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public List<UserOptimistic> getUsers() {

        List<UserOptimistic> userOptimistics = service.findAll();
        return userOptimistics;
    }

    @PostMapping(value = "/optimistic")
    public UserOptimistic saveUserOptimistic(@RequestBody UserOptimistic userOptimistic) {

        UserOptimistic savedUserOptimistic = service.save(userOptimistic);
        return savedUserOptimistic;
    }

    @PostMapping(value = "/pessimistic")
    public UserPessimistic saveUserPessimistic(@RequestBody UserPessimistic userPessimistic) {

        UserPessimistic savedUserPessimistic = service.save(userPessimistic);
        return savedUserPessimistic;
    }

    @PutMapping(value = "/pessimistic/{delay}")
    public UserPessimistic updateUserPessimistic(@PathVariable("delay") Long delay, @RequestBody UserPessimistic userPessimistic) throws InterruptedException {

        UserPessimistic updatedUserPessimistic = service.updatePessimistic(userPessimistic, delay);
        return updatedUserPessimistic;
    }
}
