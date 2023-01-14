package io.munzil.munzil.domain.question.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionVO {

    private Long questionId;
    private String questionName;

    @QueryProjection
    public QuestionVO(Long questionId, String questionName) {
        this.questionId = questionId;
        this.questionName = questionName;
    }
}
