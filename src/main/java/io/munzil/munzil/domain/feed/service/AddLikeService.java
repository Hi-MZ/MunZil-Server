package io.munzil.munzil.domain.feed.service;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.exception.FeedNotFoundException;
import io.munzil.munzil.domain.like.domain.FeedLike;
import io.munzil.munzil.domain.like.domain.repository.FeedLikeRepository;

import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddLikeService {

    private final FeedLikeRepository feedLikeRepository;
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (isNotAlreadyLike(feed, user)) {
            feedLikeRepository.save(FeedLike.builder()
                    .feed(feed)
                    .user(user)
                    .build());
        }
    }

    private boolean isNotAlreadyLike(Feed feed, User user) {
        return feedLikeRepository.findByFeedAndUser(feed, user).isEmpty();
    }
}
