package io.munzil.munzil.domain.feed.domain.repository;


import io.munzil.munzil.domain.feed.domain.repository.vo.FeedConditionVO;
import io.munzil.munzil.domain.feed.domain.repository.vo.FeedDetailsVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedRepositoryCustom {

    FeedDetailsVO queryFeedDetails(Long FeedId, Long userId);

    Slice<FeedDetailsVO> queryFeedPagesByCursor(FeedConditionVO feedConditionVO, Pageable pageable);

    Slice<FeedDetailsVO> queryFeedPagesByOffset(FeedConditionVO feedConditionVO, Pageable pageable);

}
