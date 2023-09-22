package com.bazzi.cherryfeed.apps.file.service;

import com.bazzi.cherryfeed.apps.file.domain.FileStore;
import com.bazzi.cherryfeed.apps.file.dto.FileUploadResponseDto;
import com.bazzi.cherryfeed.apps.file.repository.FileDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import javax.imageio.stream.FileImageOutputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final FileDataRepository fileDataRepository;
    private static final String FOLDER_PATH = "C:\\cherry-feed-server\\src\\main\\resources\\files\\";

    public FileUploadResponseDto uploadImageToFileSystem(MultipartFile file) throws IOException {
        log.info("upload file: {}", file.getOriginalFilename());
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileStore fildstore = FileStore.builder()
                .fileName(file.getOriginalFilename())
                .type(file.getContentType())
                .saveLocation(filePath)
                .build();
        FileStore fileStore = fileDataRepository.save(fildstore);

        float quality = determineQualityBasedOnFileSize(file.getSize());

        // 파일 경로
        //file.transferTo(new File(filePath));
        saveCompressedImage(file, filePath, quality);  // 0.8f는 압축 품질을 의미합니다. 필요에 따라 조절 가능합니다.

        if (fileStore != null) {
            FileUploadResponseDto dto = FileUploadResponseDto.builder()
                    .mesage("file uploaded successfully! filePath : " + filePath)
                    .fileId(fildstore.getId())
                    .build();
            return dto;
        }

        return null;
    }

    public byte[] downloadImageFromFileSystem(Long id) throws IOException {
        FileStore fileStore = fileDataRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        String filePath = fileStore.getSaveLocation();

        log.info("download fileData: {}", fileStore);
        log.info("download filePath: {}", filePath);

        return Files.readAllBytes(new File(filePath).toPath());
    }

    public void saveCompressedImage(MultipartFile inputFile, String outputPath, float quality) throws IOException {
        BufferedImage image = ImageIO.read(inputFile.getInputStream());

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);  // 0.5f ~ 1.0f (1.0f means no compression)

        try (FileImageOutputStream output = new FileImageOutputStream(new File(outputPath))) {
            writer.setOutput(output);
            writer.write(null, new IIOImage(image, null, null), param);
        }
    }

    private float determineQualityBasedOnFileSize(long fileSize) {
        final long ONE_MB = 1024 * 1024;  // 1 MB in bytes
        if (fileSize <= ONE_MB) {
            return 0.5f;
        } else if (fileSize <= 3 * ONE_MB) {
            return 0.3f;
        } else if (fileSize <= 5 * ONE_MB) {
            return 0.1f;
        } else {
            return 0.05f;
        }
    }
}
