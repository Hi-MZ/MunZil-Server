package io.munzil.munzil.global.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class UserNotFoundException extends MunZilException {

    public static final MunZilException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(GlobalErrorCode.USER_NOT_FOUND);
    }
}
