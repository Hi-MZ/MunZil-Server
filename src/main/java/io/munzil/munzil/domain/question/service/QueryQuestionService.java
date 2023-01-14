package io.munzil.munzil.domain.question.service;

import io.munzil.munzil.domain.question.domain.repository.QuestionRepository;
import io.munzil.munzil.domain.question.presentation.dto.response.QuestionListResponse;
import io.munzil.munzil.domain.question.presentation.dto.response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryQuestionService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public QuestionListResponse execute() {
        List<QuestionResponse> questions = questionRepository.findAll()
                .stream()
                .map(QuestionResponse::new)
                .collect(Collectors.toList());

        return new QuestionListResponse(questions.size(), questions);
    }
}
