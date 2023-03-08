package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.UserJoinRequest;
import com.bazzi.cherryfeed.model.User2;
import com.bazzi.cherryfeed.repository.UserRepository;
import com.bazzi.cherryfeed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/join") // URI=  api/v1/users/join - post요청시 회원가입 진행
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto){
        userService.join(dto.getUserName(),dto.getPassword());
        return ResponseEntity.ok().body("회원가입이 성공하였습니다.");
    };











    /*

    @Autowired
    private UserRepository userRepository;

    // 모든 유저 조회
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<User2>> getAllusers(){
        List<User2> user = userRepository.findAll();
        return new ResponseEntity<List<User2>>(user, HttpStatus.OK);

    }

    @PostMapping("/user")
    public User2 create(@RequestBody User2 user){
        return userRepository.save(user);
    }



    @GetMapping("/user/{id}")
    public Optional read(@PathVariable Long id){
        Optional<User2> userOptional = userRepository.findById(id);
        //userOptional.ifPresent(System.out::println);

        //return "successfully executed";
        return userOptional;
    } */





}