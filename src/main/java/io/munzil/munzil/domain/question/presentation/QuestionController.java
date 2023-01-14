package io.munzil.munzil.domain.question.presentation;

import io.munzil.munzil.domain.question.presentation.dto.response.QueryFeedQuestionPagesResponse;
import io.munzil.munzil.domain.question.presentation.dto.response.QuestionListResponse;
import io.munzil.munzil.domain.question.service.QueryFeedQuestionPagesService;
import io.munzil.munzil.domain.question.service.QueryQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/questions")
@RestController
public class QuestionController {

    private final QueryQuestionService queryQuestionService;
    private final QueryFeedQuestionPagesService queryFeedQuestionPagesService;

    @Operation(summary = "총 질문 정보")
    @GetMapping
    public QuestionListResponse getAllQuestion() {
        return queryQuestionService.execute();
    }

    @Operation(summary = "카테고리 게시글 정보")
    @GetMapping("/{question-id}")
    public QueryFeedQuestionPagesResponse getQuestionFeeds(@RequestParam(defaultValue = "0") Long cursorId, @PathVariable("question-id") Long questionId) {
        return queryFeedQuestionPagesService.execute(questionId, cursorId);
    }
}
