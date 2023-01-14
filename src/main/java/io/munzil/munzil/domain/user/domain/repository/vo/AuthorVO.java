package io.munzil.munzil.domain.user.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class AuthorVO {

    private final Long userId;
    private final String nickname;
    private final String profileImageUrl;

    @QueryProjection
    public AuthorVO(Long userId, String nickname, String profileImageUrl) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}