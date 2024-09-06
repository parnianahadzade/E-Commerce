package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image uploadImageToFileSystem(MultipartFile file) throws IOException;

    Image findByIdAndDeletedFalse(Long id) throws NoContentException;

}
