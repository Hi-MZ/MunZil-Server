package io.munzil.munzil.domain.follow.domain;


import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_follow")
public class Follow extends BaseTimeEntity {

    @EmbeddedId
    private FollowId id;

    @MapsId("user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("targetUser")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", nullable = false)
    private User targetUser;

    @Builder
    public Follow(FollowId id) {
        this.id = id;
    }
}