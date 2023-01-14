package io.munzil.munzil.domain.viewcount.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class FeedViewCountNotFoundException extends MunZilException {

    public static final MunZilException EXCEPTION = new FeedViewCountNotFoundException();

    public FeedViewCountNotFoundException() {
        super(GlobalErrorCode.FEED_VIEW_COUNT_NOT_FOUND);
    }
}