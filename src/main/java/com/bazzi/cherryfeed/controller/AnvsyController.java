package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.AnvsyRequest;
import com.bazzi.cherryfeed.service.AnvsyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "기념일")
@RestController
@RequestMapping(value = "api/v1/anvsy" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class AnvsyController {

    private final AnvsyService anvsyService;

    @ApiOperation(value = "기념일등록")
    @PostMapping("/post-anvsy")
    public ResponseEntity<String> createAnvsy(Authentication authentication, @RequestBody AnvsyRequest anvsyRequest){
        String userEmail = authentication.getName();//이메일
        anvsyService.createAnvsy(userEmail,anvsyRequest);
        return ResponseEntity.ok().body("등록완료.");
    }
}
