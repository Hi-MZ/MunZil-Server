package io.munzil.munzil.domain.question.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionListResponse {

    private final Integer totalCount;

    private final List<QuestionResponse> questionList;

}
