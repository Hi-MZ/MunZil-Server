package io.munzil.munzil.global.enums;

import io.munzil.munzil.global.utils.convert.Constant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortPageType implements Constant {

    LATEST("LATEST"),
    POPULAR("POPULAR");

    private final String code;

}
