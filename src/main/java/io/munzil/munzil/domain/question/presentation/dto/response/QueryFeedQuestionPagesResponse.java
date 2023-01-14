package io.munzil.munzil.domain.question.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryFeedQuestionPagesResponse {

    List<QueryFeedQuestionDetailsResponse> questionFeedList;

    Boolean hasNext;

    private Integer feedCount;
}
