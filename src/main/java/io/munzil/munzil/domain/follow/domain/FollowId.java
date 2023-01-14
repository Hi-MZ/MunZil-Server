package io.munzil.munzil.domain.follow.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode
public class FollowId implements Serializable {

    private Long user;

    private Long targetUser;

    @Builder
    public FollowId(Long user, Long targetUser) {
        this.user = user;
        this.targetUser = targetUser;
    }
}