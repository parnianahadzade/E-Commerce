package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image uploadImageToFileSystem(MultipartFile file) throws IOException;

}
