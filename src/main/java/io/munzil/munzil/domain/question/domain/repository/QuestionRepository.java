package io.munzil.munzil.domain.question.domain.repository;


import io.munzil.munzil.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
