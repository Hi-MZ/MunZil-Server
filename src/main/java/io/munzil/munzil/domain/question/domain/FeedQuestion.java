package io.munzil.munzil.domain.question.domain;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed_question")
public class FeedQuestion extends BaseTimeEntity {

    @EmbeddedId
    private FeedQuestionId id;

    @MapsId("feedId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Builder
    public FeedQuestion(Feed feed, Question question) {
        this.id = new FeedQuestionId(feed.getId());
        this.feed = feed;
        this.question = question;
    }

    public void updateFeedQuestion(Feed feed, Question question) {
        this.feed = feed;
        this.question = question;
    }
}
