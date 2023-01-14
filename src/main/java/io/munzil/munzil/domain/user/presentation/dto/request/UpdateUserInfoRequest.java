package io.munzil.munzil.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateUserInfoRequest {

    @NotBlank(message = "nickname은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 20, message = "nickname은 20글자 이하여야 합니다.")
    private String nickname;

    @NotNull(message = "isDefaultProfile는 Null을 허용하지 않습니다.")
    private Boolean isDefaultProfile;

}
