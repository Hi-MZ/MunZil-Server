package io.munzil.munzil.domain.question.presentation.dto.response;

import io.munzil.munzil.domain.question.domain.Question;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QuestionResponse {

    private final Long questionId;

    private final String questionName;

    public QuestionResponse(Question question) {
        this.questionId = question.getId();
        this.questionName = question.getQuestionName();
    }

    @Builder
    public QuestionResponse(Long questionId, String questionName) {
        this.questionId = questionId;
        this.questionName = questionName;
    }
}
