package io.munzil.munzil.domain.user.service;

import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.user.presentation.dto.response.QueryMyProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyProfileService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyProfileResponse execute() {
        User user = userFacade.getCurrentUser();

        return QueryMyProfileResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
