package io.munzil.munzil.domain.follow.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class FollowAlreadyExistsException extends MunZilException {

    public static final MunZilException EXCEPTION = new FollowAlreadyExistsException();

    private FollowAlreadyExistsException() {
        super(GlobalErrorCode.FOLLOW_ALREADY_EXISTS);
    }
}
