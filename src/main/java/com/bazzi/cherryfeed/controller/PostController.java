package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.AnvsyRequestDto;
import com.bazzi.cherryfeed.domain.dto.PostRequestDto;
import com.bazzi.cherryfeed.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = "기념일")
@RestController
@RequestMapping(value = "/api/v1/post" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @ApiOperation(value = "게시물등록")
    @PostMapping("/post-post")
    public ResponseEntity<String> createAnvsy(Authentication authentication, @RequestBody PostRequestDto postRequestDto){
        String userEmail = authentication.getName();//이메일
        postService.createPost(userEmail, postRequestDto);
        return ResponseEntity.ok().body("등록완료.");
    }
    //@ApiOperation(value = "게시물조회")
    //@GetMapping

    @ApiOperation(value = "게시물수정")
    @PutMapping("/post-post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        postService.updatePost(id,postRequestDto);
        return ResponseEntity.ok().body("등록완료.");
    }
    @ApiOperation(value = "게시물삭제")
    @DeleteMapping("/post-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        postService.deletePost(id);
        return ResponseEntity.ok().body("삭제완료.");
    }

}
