package io.munzil.munzil.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryMyFeedPagesResponse {

    List<QueryMyFeedDetailsResponse> feedList;

    Boolean hasNext;

    private Integer feedCount;

}
