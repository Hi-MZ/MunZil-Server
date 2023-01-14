package io.munzil.munzil.domain.question.domain.repository;

import io.munzil.munzil.domain.question.domain.FeedQuestion;
import io.munzil.munzil.domain.question.domain.FeedQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedQuestionRepository extends JpaRepository<FeedQuestion, FeedQuestionId> {

    FeedQuestion findFeedQuestionByFeedId(Long feedId);

}
