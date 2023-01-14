package io.munzil.munzil.domain.feed.service;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.exception.FeedNotFoundException;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.exception.NotValidUserException;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.viewcount.domain.FeedViewCount;
import io.munzil.munzil.domain.viewcount.domain.repository.FeedViewCountRepository;
import io.munzil.munzil.domain.viewcount.exception.FeedViewCountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteFeedService {

    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final FeedViewCountRepository feedViewCountRepository;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        FeedViewCount feedViewCount = feedViewCountRepository.findById(feedId)
                .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

        if (!user.getId().equals(feed.getUser().getId())) {
            throw NotValidUserException.EXCEPTION;
        }

        user.subFeed();
        feedRepository.delete(feed);
        feedViewCountRepository.delete(feedViewCount);
    }
}
