package io.munzil.munzil.global.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class SaveImageFalseException extends MunZilException {
    public static final MunZilException EXCEPTION =
            new SaveImageFalseException();

    private SaveImageFalseException() {
        super(GlobalErrorCode.IMAGE_NOT_FOUND);
    }
}
