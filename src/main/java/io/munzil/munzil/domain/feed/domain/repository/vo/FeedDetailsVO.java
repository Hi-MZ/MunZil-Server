package io.munzil.munzil.domain.feed.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import io.munzil.munzil.domain.user.domain.repository.vo.AuthorVO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FeedDetailsVO {

    private AuthorVO authorVO;
    private String content;
    private Long feedId;
    private Long likeCount;
    private Boolean isLike;
    private LocalDateTime createdAt;

    @QueryProjection
    public FeedDetailsVO(AuthorVO authorVO, String content, Long feedId, Long likeCount,
                         Boolean isLike, LocalDateTime createdAt) {
        this.authorVO = authorVO;
        this.content = content;
        this.feedId = feedId;
        this.likeCount = likeCount;
        this.isLike = isLike;
        this.createdAt = createdAt;
    }
}
