package com.bazzi.cherryfeed.apps.anvsy.controller;

import com.bazzi.cherryfeed.apps.anvsy.dto.AnvsyRequestDto;
import com.bazzi.cherryfeed.apps.anvsy.dto.AnvsyResponseDto;
import com.bazzi.cherryfeed.apps.anvsy.service.AnvsyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "기념일")
@RestController
@RequestMapping(value = "/api/v1/anvsy" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class AnvsyController {

    private final AnvsyService anvsyService;
    @ApiOperation(value = "기념일조회")
    @GetMapping
    public ResponseEntity<List> readAnvsy(Authentication authentication){
        String userEmail = authentication.getName();//이메일
        List<AnvsyResponseDto> anvsyResponseDtoList = anvsyService.readAnvsy(userEmail);
        return ResponseEntity.ok().body(anvsyResponseDtoList);
    }

    @ApiOperation(value = "기념일등록")
    @PostMapping("/post-anvsy")
    public ResponseEntity<String> createAnvsy(Authentication authentication, @RequestBody AnvsyRequestDto anvsyRequestDto){
        String userEmail = authentication.getName();//이메일
        anvsyService.createAnvsy(userEmail, anvsyRequestDto);
        return ResponseEntity.ok().body("등록완료.");
    }

    @ApiOperation(value = "기념일수정")
    @PutMapping("/post-anvsy/{id}")
    public ResponseEntity<String> updateAnvsy(@PathVariable Long id, @RequestBody AnvsyRequestDto anvsyRequestDto){
        anvsyService.updateAnvsy(id,anvsyRequestDto);
        return ResponseEntity.ok().body("수정완료.");
    }

    @ApiOperation(value = "기념일삭제")
    @DeleteMapping("/post-anvsy/{id}")
    public ResponseEntity<String> deleteAnvsy(@PathVariable Long id){
        anvsyService.deleteAnvsy(id);
        return ResponseEntity.ok().body("삭제완료.");
    }
}
