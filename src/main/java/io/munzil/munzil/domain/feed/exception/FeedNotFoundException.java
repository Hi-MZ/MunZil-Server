package io.munzil.munzil.domain.feed.exception;

import io.munzil.munzil.global.error.exception.GlobalErrorCode;
import io.munzil.munzil.global.error.exception.MunZilException;

public class FeedNotFoundException extends MunZilException {

    public static final MunZilException EXCEPTION = new FeedNotFoundException();

    public FeedNotFoundException() {
        super(GlobalErrorCode.FEED_NOT_FOUND);
    }
}