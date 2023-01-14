package io.munzil.munzil.domain.like.domain.repository;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.like.domain.FeedLike;
import io.munzil.munzil.domain.like.domain.FeedLikeId;
import io.munzil.munzil.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLike, FeedLikeId> {

    Optional<FeedLike> findByFeedAndUser(Feed feed, User user);

    Long countByUser(User user);
}
