package io.munzil.munzil.domain.user.service;

import io.munzil.munzil.domain.feed.domain.FeedImage;
import io.munzil.munzil.domain.feed.domain.repository.FeedImageRepository;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedConditionVO;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedDetailsVO;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.user.presentation.dto.response.QueryMyBookmarksDetailsResponse;
import io.munzil.munzil.domain.user.presentation.dto.response.QueryMyBookmarksPagesResponse;
import io.munzil.munzil.domain.viewcount.domain.FeedViewCount;
import io.munzil.munzil.domain.viewcount.domain.repository.FeedViewCountRepository;
import io.munzil.munzil.domain.viewcount.exception.FeedViewCountNotFoundException;
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
public class QueryMyBookmarksPagesService {

    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;
    private final static String ORDER = "BOOKMARK";

    @Transactional(readOnly = true)
    public QueryMyBookmarksPagesResponse execute(Integer pageId, Long categoryId) {

        User user = userFacade.getCurrentUser();

        Slice<FeedDetailsVO> feedList = feedRepository.queryFeedPagesByOffset(
                FeedConditionVO.builder()
                        .userId(user.getId())
                        .pageId(pageId)
                        .orders(ORDER)
                        .build(),
                PagingSupportUtil.applyPageSize()
        );

        List<QueryMyBookmarksDetailsResponse> queryMyBookmarksDetailsResponseList = new ArrayList<>();

        for (FeedDetailsVO feedDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

            queryMyBookmarksDetailsResponseList.add(
                    buildQueryMyBookmarkDetailsResponse(feedDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryMyBookmarksPagesResponse(queryMyBookmarksDetailsResponseList, feedList.hasNext(), queryMyBookmarksDetailsResponseList.size(), pageId);
    }

    private QueryMyBookmarksDetailsResponse buildQueryMyBookmarkDetailsResponse(FeedDetailsVO feedDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryMyBookmarksDetailsResponse.builder()
                .feedId(feedDetailsVO.getFeedId())
                .imageUrl(imageUrl)
                .createdAt(feedDetailsVO.getCreatedAt())
                .content(feedDetailsVO.getContent())
                .isLike(feedDetailsVO.getIsLike())
                .likeCount(feedDetailsVO.getLikeCount())
                .viewCount(feedViewCount)
                .build();
    }
}
