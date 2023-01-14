package io.munzil.munzil.domain.user.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class AlreadyNicknameExistException extends MunZilException {

    public static final MunZilException EXCEPTION =
            new AlreadyNicknameExistException();

    private AlreadyNicknameExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }
}
