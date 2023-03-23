package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.FileUploadResponseDto;
import com.bazzi.cherryfeed.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Api(tags = "파일업로드다운로드")
@RestController
@RequestMapping(value = "api/v1/file" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor

public class FileController {
    private final StorageService storageService;
    @ApiOperation(value = "파일업로드")
    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
        FileUploadResponseDto dto = storageService.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }
    @ApiOperation(value = "파일다운로드")
    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable("fileName") String fileName) throws IOException {
        byte[] downloadImage = storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }
}
