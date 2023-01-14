package io.munzil.munzil.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MunZilException extends RuntimeException {

    private final GlobalErrorCode errorCode;

}
