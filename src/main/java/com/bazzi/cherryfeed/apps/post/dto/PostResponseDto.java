package com.bazzi.cherryfeed.apps.post.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "게시판조회응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private Long imgId;
    private String location;
    private Date postAt;
    private String postContent;
    private String postNm;
    private Long postView;
    private Long scrap;
}
