package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.model.entity.Image;
import com.mftplus.ecommerce.repository.ImageRepository;
import com.mftplus.ecommerce.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private static final String FOLDER_PATH = "src/main/resources/static/productImages";

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public Image uploadImageToFileSystem(MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(FOLDER_PATH , file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(String.valueOf(fileNameAndPath))
                .build();

        return imageRepository.save(image);
    }

}
