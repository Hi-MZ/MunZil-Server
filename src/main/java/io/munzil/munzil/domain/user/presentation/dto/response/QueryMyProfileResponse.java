package io.munzil.munzil.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyProfileResponse {

    private final Long userId;

    private final String profileImageUrl;

    private final String nickname;

}
