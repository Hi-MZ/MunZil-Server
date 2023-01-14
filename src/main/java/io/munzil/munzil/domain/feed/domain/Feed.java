package io.munzil.munzil.domain.feed.domain;

import io.munzil.munzil.domain.like.domain.FeedLike;
import io.munzil.munzil.domain.question.domain.FeedQuestion;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed")
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedLike> feedLikes;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedImage> feedImages;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedQuestion> feedQuestions;

    @Builder
    public Feed(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public void updateFeed(String content) {
        this.content = content;
    }

}
