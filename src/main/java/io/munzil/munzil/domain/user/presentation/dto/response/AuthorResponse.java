package io.munzil.munzil.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorResponse {

    private final Long userId;

    private final String nickname;

    private final String profileImageUrl;

}
