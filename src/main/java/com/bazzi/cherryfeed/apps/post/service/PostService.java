package com.bazzi.cherryfeed.apps.post.service;

import com.bazzi.cherryfeed.apps.calendar.domain.CoupleCalendar;
import com.bazzi.cherryfeed.apps.post.domain.Post;
import com.bazzi.cherryfeed.apps.account.domain.User;
import com.bazzi.cherryfeed.apps.post.dto.PageResponse;
import com.bazzi.cherryfeed.apps.post.dto.PostRequestDto;
import com.bazzi.cherryfeed.apps.post.dto.PostResponseDto;
import com.bazzi.cherryfeed.apps.calendar.repository.CoupleCalendarRepository;
import com.bazzi.cherryfeed.apps.post.repository.PostRepository;
import com.bazzi.cherryfeed.apps.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    public String updatePost(Long id,PostRequestDto postRequestDto){
        Long calendarId = postRequestDto.getCalendarId();
        String postNm = postRequestDto.getPostNm();
        String location = postRequestDto.getLocation();
        String postContent = postRequestDto.getPostContent();
        Long imgId = postRequestDto.getImgId();

        CoupleCalendar coupleCalendarId = coupleCalendarRepository.findById(calendarId).get();
        Post post = postRepository.findById(id).get();
        post.updatePost(coupleCalendarId,postNm,postContent,location,imgId);
        return "SUCCES";
    }
    @Transactional
    public String deletePost(Long id ){
        postRepository.deleteById(id);
        return "SUCCES";
    }
    public PageResponse findAll(int pageNo, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));//0페이지에서 3개 가져와
        Page<Post> page = postRepository.findAll(pageRequest);

        long totalElements = page.getTotalElements(); //토탈 카운트
        int number = page.getNumber();

        ArrayList<PostResponseDto> postResponseDtos = new ArrayList<>();

        List<Post> content = page.getContent(); //3개 데이터들

        for (Post post : content) {
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .postAt(post.getPostAt())
                    .postContent(post.getPostContent())
                    .postView(post.getPostView())
                    .postNm(post.getPostNm())
                    .scrap(post.getScrap())
                    .id(post.getId())
                    .location(post.getLocation())
                    .imgId(post.getImgId())
                    .build();
            postResponseDtos.add(postResponseDto);
        }
        PageResponse pageResponse = PageResponse.builder()
                .content(postResponseDtos)
                .last(page.isLast())
                .pageNo(pageNo)
                .totalElements(totalElements)
                .totalPages(page.getTotalPages())
                .pageSize(pageSize)
                .build();
        return pageResponse;
    }
}
