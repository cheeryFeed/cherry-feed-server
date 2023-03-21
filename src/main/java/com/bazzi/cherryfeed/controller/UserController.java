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
@Api(tags = "회원가입/로그인")
@RestController
@RequestMapping(value = "/api/v1/users" , produces = "application/json; charset=utf8") //자꾸 한글깨지길래 utf-8변환함;
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JoinService joinService;

    @ApiOperation(value = "회원가입" , notes = "회원가입을 진행한다. 회원가입이 성공하면 200응답과 함께 회원가입이 성공하였습니다.가 반환됨.")
    @PostMapping("/join") // URI=  api/v1/users/join - post요청시 회원가입 진행
    public ResponseEntity<String> join(@RequestBody UserJoinRequest userJoinRequestDto){
        userService.join(userJoinRequestDto);
        return ResponseEntity.ok().body("회원가입이 성공하였습니다.");
    };

    @ApiOperation(value = "로그인" , notes = "로그인을 진행한다. 회원가입이 성공하면 200응답과 함께 바디에 토큰이 발급됨")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto) {
        String token = userService.login(dto.getEmail(), dto.getPw());
        return ResponseEntity.ok().body(token);
    }

    //************회원가입 닉네임 중복 조회 API*****************
    @ApiOperation(value = "닉네임중복조회" , notes = "회원가입절차에서 닉네임조회기능. 사용가능하면 200응답과 함께 닉네임 사용 가능.가 반환됨.")
    @GetMapping("/duplicationcheck/nickname")
    public ResponseEntity<String> duplicationcheckNickname(

            @RequestParam(name = "nickname",required = true) String nickname){
        joinService.duplicationCheckNicknameService(nickname);
        return ResponseEntity.ok().body("닉네임 사용 가능.");
    }
    //************연결코드 발급 API*****************
    @ApiOperation(value = "연결코드생성" , notes = "랜덤으로 8자리가 생성된다 저장기능X. 응답으로 200과 코드가 전달됨.")
    @GetMapping("/create/connectcode")
    public ResponseEntity<String> createConnectCode(){
        String createConnectCode = joinService.getCreateConnectCode();
        return ResponseEntity.ok().body(createConnectCode);
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