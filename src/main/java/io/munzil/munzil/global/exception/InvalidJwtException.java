package io.munzil.munzil.global.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class InvalidJwtException extends MunZilException {

    public static final MunZilException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(GlobalErrorCode.INVALID_JWT);
    }
}
