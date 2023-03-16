package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.UserJoinRequest;
import com.bazzi.cherryfeed.domain.dto.UserLoginRequest;
import com.bazzi.cherryfeed.model.User2;
import com.bazzi.cherryfeed.repository.UserRepository;
import com.bazzi.cherryfeed.service.JoinService;
import com.bazzi.cherryfeed.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Api(tags = "루카")
@RestController
@RequestMapping(value = "/api/v1/users" , produces = "application/json; charset=utf8") //자꾸 한글깨지길래 utf-8변환함;
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JoinService joinService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/join") // URI=  api/v1/users/join - post요청시 회원가입 진행
    public ResponseEntity<String> join(@RequestBody UserJoinRequest userJoinRequestDto){
        userService.join(userJoinRequestDto);
        return ResponseEntity.ok().body("회원가입이 성공하였습니다.");
    };

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto) {
        String token = userService.login(dto.getEmail(), dto.getPw());
        return ResponseEntity.ok().body(token);
    }

    //************회원가입 닉네임 중복 조회 API*****************
    @GetMapping("/duplicationcheck/nickname")
    public ResponseEntity<String> duplicationcheckNickname(

            @RequestParam(name = "nickname",required = true) String nickname){
        joinService.duplicationCheckNicknameService(nickname);
        return ResponseEntity.ok().body("닉네임 사용 가능.");
    }
    //************연결코드 발급 API*****************
    @GetMapping("/create/connectcode")
    public ResponseEntity<String> createConnectCode(){
        joinService.
        return ResponseEntity.ok().body("닉네임 사용 가능.");
    }











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