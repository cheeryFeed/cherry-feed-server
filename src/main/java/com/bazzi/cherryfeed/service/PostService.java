package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.CoupleCalendar;
import com.bazzi.cherryfeed.domain.Post;
import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.domain.dto.PostRequestDto;
import com.bazzi.cherryfeed.repository.CoupleCalendarRepository;
import com.bazzi.cherryfeed.repository.PostRepository;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CoupleCalendarRepository coupleCalendarRepository;

    public String createPost(String userEmail, PostRequestDto postRequestDto){
        User fidedUser = userRepository.findUserByEmail(userEmail); //유저
        CoupleCalendar coupleCalendar = coupleCalendarRepository.findById(postRequestDto.getCalendarId()).get();

        Post post = Post.builder()
                .postNm(postRequestDto.getPostNm())
                .postContent(postRequestDto.getPostContent())
                .location(postRequestDto.getLocation())
                .imgId(postRequestDto.getImgId())
                .createdById(fidedUser)
                .calId(coupleCalendar)
                .build();
        postRepository.save(post);
        return "SUCCES";
    }
}
