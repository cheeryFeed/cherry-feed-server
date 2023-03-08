package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.model.User;
import com.bazzi.cherryfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 모든 유저 조회
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<User>> getAllusers(){
        List<User> user = userRepository.findAll();
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);

    }

    @PostMapping("/user")
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }



    @GetMapping("/user/{id}")
    public Optional read(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        //userOptional.ifPresent(System.out::println);

        //return "successfully executed";
        return userOptional;
    }


}