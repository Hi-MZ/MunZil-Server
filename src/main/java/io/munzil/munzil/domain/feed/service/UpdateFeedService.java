package io.munzil.munzil.domain.feed.service;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.feed.domain.FeedImage;
import io.munzil.munzil.domain.feed.domain.repository.FeedImageRepository;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.exception.FeedNotFoundException;
import io.munzil.munzil.domain.feed.presentation.dto.request.UpdateFeedRequest;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.exception.NotValidUserException;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.infrastructure.image.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateFeedService {

    private final FeedRepository feedRepository;
    private final UserFacade userFacade;
    private final FeedImageRepository feedImageRepository;
    private final S3Service s3Service;

    @Transactional
    public void execute(List<MultipartFile> images, UpdateFeedRequest request, Long feedId) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (!user.getId().equals(feed.getUser().getId())) {
            throw NotValidUserException.EXCEPTION;
        }

        feedImageRepository.deleteAllByFeed(feed);

        if (images != null) {
            images.stream()
                    .map(s3Service::uploadImage)
                    .map(image -> FeedImage.builder()
                            .feed(feed)
                            .imageUrl(image)
                            .build()
                    )
                    .forEach(feedImageRepository::save);
        }
        feed.updateFeed(request.getContent());
    }
}
