package io.munzil.munzil.domain.feed.domain.repository.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedConditionVO {

    private Long userId;
    private Long cursorId;
    private Integer pageId;
    private Long findUserId;
    private String orders;

}
