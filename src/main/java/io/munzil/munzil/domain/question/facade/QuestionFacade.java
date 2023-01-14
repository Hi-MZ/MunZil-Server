package io.munzil.munzil.domain.question.facade;

import io.munzil.munzil.domain.question.domain.Question;
import io.munzil.munzil.domain.question.domain.repository.QuestionRepository;
import io.munzil.munzil.domain.question.exception.QuestionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QuestionFacade {

    private final QuestionRepository questionRepository;

    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> QuestionNotFoundException.EXCEPTION);
    }
}
