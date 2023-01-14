package io.munzil.munzil.domain.follow.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FollowInfo {

    private final Long userId;

    private final String nickname;

    private final String profileImageUrl;

    private final boolean isFollow;
}
