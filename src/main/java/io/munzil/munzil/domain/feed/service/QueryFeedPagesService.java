package io.munzil.munzil.domain.feed.service;

import io.munzil.munzil.domain.feed.domain.FeedImage;
import io.munzil.munzil.domain.feed.domain.repository.FeedImageRepository;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedConditionVO;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedDetailsVO;
import io.munzil.munzil.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import io.munzil.munzil.domain.feed.presentation.dto.response.QueryFeedPagesResponse;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.domain.repository.vo.AuthorVO;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.user.presentation.dto.response.AuthorResponse;
import io.munzil.munzil.domain.viewcount.domain.FeedViewCount;
import io.munzil.munzil.domain.viewcount.domain.repository.FeedViewCountRepository;
import io.munzil.munzil.domain.viewcount.exception.FeedViewCountNotFoundException;
import io.munzil.munzil.global.enums.SortPageType;
import io.munzil.munzil.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryFeedPagesService {

    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryFeedPagesResponse execute(Long cursorId, Integer pageId, SortPageType sortType) {
        User user = userFacade.getCurrentUser();

        Slice<FeedDetailsVO> feedList = getFeedList(
                FeedConditionVO.builder()
                        .cursorId(PagingSupportUtil.applyCursorId(cursorId))
                        .pageId(pageId)
                        .userId(user.getId())
                        .orders(sortType.getCode())
                        .build(),
                sortType);

        List<QueryFeedDetailsResponse> queryFeedDetailsResponseList = new ArrayList<>();

        for (FeedDetailsVO feedDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

            queryFeedDetailsResponseList.add(
                    buildFeedDetailsResponse(feedDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryFeedPagesResponse(queryFeedDetailsResponseList, feedList.hasNext(), queryFeedDetailsResponseList.size(), pageId);
    }

    private AuthorResponse buildAuthorResponse(AuthorVO authorVO) {
        return AuthorResponse.builder()
                .userId(authorVO.getUserId())
                .nickname(authorVO.getNickname())
                .profileImageUrl(authorVO.getProfileImageUrl())
                .build();
    }

    private QueryFeedDetailsResponse buildFeedDetailsResponse(FeedDetailsVO feedDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryFeedDetailsResponse.builder()
                .author(buildAuthorResponse(feedDetailsVO.getAuthorVO()))
                .feedId(feedDetailsVO.getFeedId())
                .imageUrl(imageUrl)
                .createdAt(feedDetailsVO.getCreatedAt())
                .content(feedDetailsVO.getContent())
                .isLike(feedDetailsVO.getIsLike())
                .likeCount(feedDetailsVO.getLikeCount())
                .viewCount(feedViewCount)
                .build();
    }

    private Slice<FeedDetailsVO> getFeedList(FeedConditionVO feedConditionVO, SortPageType sortPageType) {
        return switch (sortPageType) {
            case LATEST -> feedRepository.queryFeedPagesByCursor(feedConditionVO, PagingSupportUtil.applyPageSize());
            case POPULAR -> feedRepository.queryFeedPagesByOffset(feedConditionVO, PagingSupportUtil.applyPageSize());
            default -> null;
        };
    }
}
