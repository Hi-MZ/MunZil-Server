package io.munzil.munzil.domain.feed.presentation.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryFeedPagesResponse {

    List<QueryFeedDetailsResponse> feedList;

    Boolean hasNext;

    private Integer feedCount;

    private Integer pageId;

}
