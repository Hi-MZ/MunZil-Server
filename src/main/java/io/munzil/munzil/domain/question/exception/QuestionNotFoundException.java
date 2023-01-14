package io.munzil.munzil.domain.question.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class QuestionNotFoundException extends MunZilException {

    public static final MunZilException EXCEPTION =
            new QuestionNotFoundException();

    private QuestionNotFoundException() {
        super(GlobalErrorCode.QUESTION_NOT_FOUND);
    }
}
