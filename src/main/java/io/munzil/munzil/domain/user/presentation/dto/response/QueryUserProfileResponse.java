package io.munzil.munzil.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryUserProfileResponse {

    private final Long userId;

    private final String profileImageUrl;

    private final String nickname;

    @Getter
    @Builder
    public static class Feed {

        private Long feedId;

        private String content;

        private List<String> imageUrl;

    }
}
