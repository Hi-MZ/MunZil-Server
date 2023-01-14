package io.munzil.munzil.domain.user.service;

import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import io.munzil.munzil.infrastructure.image.DefaultImage;
import io.munzil.munzil.infrastructure.image.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserInfoService {

    private final UserFacade userFacade;
    private final S3Service s3Service;

    @Transactional
    public void execute(MultipartFile image, UpdateUserInfoRequest request) {
        User user = userFacade.getCurrentUser();

        if (image != null) {
            String profileImage = s3Service.uploadImage(image);
            user.updateProfileImageUrl(profileImage);
        }

        if (request.getIsDefaultProfile()) {
            user.updateProfileImageUrl(DefaultImage.USER_PROFILE_IMAGE);
        }

        user.updateUser(request);
    }
}
