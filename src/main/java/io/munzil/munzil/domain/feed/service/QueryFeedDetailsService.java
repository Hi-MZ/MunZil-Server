package io.munzil.munzil.domain.feed.service;

import io.munzil.munzil.domain.feed.domain.Feed;
import io.munzil.munzil.domain.feed.domain.FeedImage;
import io.munzil.munzil.domain.feed.domain.repository.FeedImageRepository;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedDetailsVO;
import io.munzil.munzil.domain.feed.exception.FeedNotFoundException;
import io.munzil.munzil.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.domain.repository.vo.AuthorVO;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.user.presentation.dto.response.AuthorResponse;
import io.munzil.munzil.domain.viewcount.domain.FeedViewCount;
import io.munzil.munzil.domain.viewcount.domain.repository.FeedViewCountRepository;
import io.munzil.munzil.domain.viewcount.exception.FeedViewCountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryFeedDetailsService {

    private final FeedImageRepository feedImageRepository;
    private final FeedRepository feedRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryFeedDetailsResponse execute(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        User user = userFacade.getCurrentUser();

        FeedViewCount feedViewCount = feedViewCountRepository.findById(feedId)
                .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

        feedViewCount.addViewCount();
        feedViewCountRepository.save(feedViewCount);

        List<String> imageUrl = feedImageRepository.findByFeed(feed)
                .stream()
                .map(FeedImage::getImageUrl)
                .collect(Collectors.toList());

        FeedDetailsVO feedDetailsVO = feedRepository.queryFeedDetails(feedId, user.getId());

        return QueryFeedDetailsResponse.builder()
                .author(buildAuthorResponse(feedDetailsVO.getAuthorVO()))
                .feedId(feedDetailsVO.getFeedId())
                .content(feedDetailsVO.getContent())
                .imageUrl(imageUrl)
                .isLike(feedDetailsVO.getIsLike())
                .viewCount(feedViewCount.getViewCount())
                .createdAt(feedDetailsVO.getCreatedAt())
                .likeCount(feedDetailsVO.getLikeCount())
                .build();
    }

    private AuthorResponse buildAuthorResponse(AuthorVO authorVO) {
        return AuthorResponse.builder()
                .userId(authorVO.getUserId())
                .nickname(authorVO.getNickname())
                .profileImageUrl(authorVO.getProfileImageUrl())
                .build();
    }
}
