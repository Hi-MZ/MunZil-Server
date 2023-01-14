package io.munzil.munzil.infrastructure.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile image);

}
