package io.munzil.munzil.domain.feed.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateFeedRequest {

    @NotNull(message = "content는 Null을 허용하지 않습니다.")
    private String content;

}
