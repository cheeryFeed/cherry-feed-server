package com.bazzi.cherryfeed.apps.file.controller;

import com.bazzi.cherryfeed.apps.file.dto.FileUploadResponseDto;
import com.bazzi.cherryfeed.apps.file.service.StorageService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


//@Api(tags = "파일업로드다운로드")
@RestController
@RequestMapping(value = "/api/v1/file", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
@Slf4j

public class FileController {
    private final StorageService storageService;

    //@ApiOperation(value = "파일업로드")
    @PostMapping("/file-system")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
        FileUploadResponseDto dto = storageService.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    //@ApiOperation(value = "파일다운로드")
    @GetMapping("/file-system/{id}")
    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable("id") Long id) throws IOException {
        byte[] downloadImage = storageService.downloadImageFromFileSystem(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }
}
