package io.munzil.munzil.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryMyBookmarksPagesResponse {

    List<QueryMyBookmarksDetailsResponse> feedList;

    Boolean hasNext;

    private Integer feedCount;

    private Integer pageId;

}
