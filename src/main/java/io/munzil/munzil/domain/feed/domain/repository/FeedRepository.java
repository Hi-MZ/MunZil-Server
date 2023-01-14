package io.munzil.munzil.domain.feed.domain.repository;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeedRepository extends JpaRepository<Feed, Long>, FeedRepositoryCustom, QuerydslPredicateExecutor<Feed> {

    void deleteFeedByUser(User user);

}
