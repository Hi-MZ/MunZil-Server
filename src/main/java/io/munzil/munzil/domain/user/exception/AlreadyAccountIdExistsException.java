package io.munzil.munzil.domain.user.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class AlreadyAccountIdExistsException extends MunZilException {

    public static final MunZilException EXCEPTION =
            new AlreadyAccountIdExistsException();

    private AlreadyAccountIdExistsException() {
        super(GlobalErrorCode.ALREADY_EMAIL_EXISTS);
    }
}
