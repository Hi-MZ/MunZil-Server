package io.munzil.munzil.domain.question.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode
public class FeedQuestionId implements Serializable {

    private Long feedId;

    @Builder
    public FeedQuestionId(Long feedId) {
        this.feedId = feedId;
    }
}
