package com.mftplus.ecommerce.api.controller.image;

import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.Image;
import com.mftplus.ecommerce.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${apiPrefix}/image")
@Slf4j
@CrossOrigin
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    private ResponseEntity uploadImage(@RequestPart("file") MultipartFile file) throws IOException {

        ApiResponse response = new ApiResponse();

        try {
            Image image = imageService.uploadImageToFileSystem(file);
            response.setSuccess(true);
            response.setSuccessMessage("عکس با موفقیت بارگذاری شد.");

            Map<String, Object> data = new HashMap<>();
            data.put("image", image);
            response.setData(data);

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            throw new IOException("عکس بارگذاری نشد.");
        }

    }
}
