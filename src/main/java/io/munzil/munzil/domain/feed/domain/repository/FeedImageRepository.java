package io.munzil.munzil.domain.feed.domain.repository;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.feed.domain.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedImageRepository extends JpaRepository<FeedImage, Long> {

    void deleteAllByFeed(Feed feed);
    List<FeedImage> findByFeed(Feed feed);
    List<FeedImage> findByFeedId(Long feedId);

}
