package io.munzil.munzil.domain.user.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class NotValidUserException extends MunZilException {

    public static final MunZilException EXCEPTION =
            new NotValidUserException();

    private NotValidUserException() {
        super(GlobalErrorCode.NOT_VALID_USER);
    }
}