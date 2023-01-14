package io.munzil.munzil.domain.feed.service;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.feed.domain.FeedImage;
import io.munzil.munzil.domain.feed.domain.repository.FeedImageRepository;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.presentation.dto.request.CreateFeedRequest;
import io.munzil.munzil.domain.feed.presentation.dto.response.CreateFeedResponse;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.viewcount.domain.FeedViewCount;
import io.munzil.munzil.domain.viewcount.domain.repository.FeedViewCountRepository;
import io.munzil.munzil.infrastructure.image.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateFeedService {

    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final S3Service s3Service;

    @Transactional
    public CreateFeedResponse execute(List<MultipartFile> images, CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();

        Feed feed = Feed.builder()
                .content(request.getContent())
                .user(user)
                .build();

        feedRepository.save(feed);

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

        feedViewCountRepository.save(FeedViewCount.builder()
                .feedId(feed.getId())
                .build());

        user.addFeed();

        return new CreateFeedResponse(feed.getId());
    }
}
