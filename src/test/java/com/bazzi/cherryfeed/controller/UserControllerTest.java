package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.UserJoinRequestDto;
import com.bazzi.cherryfeed.domain.dto.UserLoginRequestDto;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("회원가입성공")
    @WithMockUser
    void join() throws Exception {
        //가라데이터
        String userName="곽치영";
        String password="1234";


        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequestDto(userName,password))))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    @WithMockUser
    void join_fail() throws Exception {
        //가라데이터
        String userName="곽치영2";
        String password="1234";
        //mocking
        when(userService.join(any(),any()))
                .thenThrow(new RuntimeException("해당 userId가 중복됩니다."));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequestDto(userName,password))))
                .andDo(print())
                .andExpect(status().isConflict());
    }
    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success() throws Exception {
        //가라데이터
        String userName="곽치영";
        String password="1234";

        when(userService.login(any(),any()))
                .thenReturn("token");

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequestDto(userName,password))))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("로그인 실패-userName없음")
    @WithMockUser
    void login_fail() throws Exception {
        //가라데이터
        String userName="곽치영";
        String password="1234";

        when(userService.login(any(),any()))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequestDto(userName,password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    @DisplayName("로그인 실패-password틀림")
    @WithMockUser
    void login_password_fail() throws Exception {
        //가라데이터
        String userName="곽치영";
        String password="1234";

        when(userService.login(any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD,""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequestDto(userName,password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }



}