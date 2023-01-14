package io.munzil.munzil.domain.user.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class AlreadyUserExistException extends MunZilException {

    public static final MunZilException EXCEPTION =
            new AlreadyUserExistException();

    private AlreadyUserExistException() {
        super(GlobalErrorCode.ALREADY_USER_EXIST);
    }
}
