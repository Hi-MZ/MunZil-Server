package io.munzil.munzil.domain.user.service;

import io.munzil.munzil.domain.feed.domain.FeedImage;
import io.munzil.munzil.domain.feed.domain.repository.FeedImageRepository;
import io.munzil.munzil.domain.feed.domain.repository.FeedRepository;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedConditionVO;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedDetailsVO;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.user.presentation.dto.response.QueryMyFeedDetailsResponse;
import io.munzil.munzil.domain.user.presentation.dto.response.QueryMyFeedPagesResponse;
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
public class QueryMyFeedPagesService {

    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyFeedPagesResponse execute(Long cursorId) {
        User user = userFacade.getCurrentUser();

        Slice<FeedDetailsVO> feedList = feedRepository.queryFeedPagesByCursor(
                FeedConditionVO.builder()
                        .userId(user.getId())
                        .cursorId(PagingSupportUtil.applyCursorId(cursorId))
                        .findUserId(user.getId())
                        .build(),
                PagingSupportUtil.applyPageSize()
        );

        List<QueryMyFeedDetailsResponse> queryMyFeedDetailsResponseList = new ArrayList<>();

        for (FeedDetailsVO feedDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

            queryMyFeedDetailsResponseList.add(
                    buildQueryMyFeedDetailsResponse(feedDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryMyFeedPagesResponse(queryMyFeedDetailsResponseList, feedList.hasNext(), queryMyFeedDetailsResponseList.size());
    }

    private QueryMyFeedDetailsResponse buildQueryMyFeedDetailsResponse(FeedDetailsVO feedDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryMyFeedDetailsResponse.builder()
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
