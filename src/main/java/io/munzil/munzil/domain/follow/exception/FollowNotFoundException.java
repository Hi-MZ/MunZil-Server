package io.munzil.munzil.domain.follow.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class FollowNotFoundException extends MunZilException {

    public static final MunZilException EXCEPTION = new FollowNotFoundException();

    private FollowNotFoundException() {
        super(GlobalErrorCode.FOLLOW_NOT_FOUND);
    }
}
