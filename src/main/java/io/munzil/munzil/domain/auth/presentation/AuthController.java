package io.munzil.munzil.domain.auth.presentation;

import io.munzil.munzil.domain.auth.presentation.dto.request.UserSignInRequest;
import io.munzil.munzil.domain.auth.presentation.dto.response.UserTokenRefreshResponse;
import io.munzil.munzil.domain.auth.presentation.dto.response.UserTokenResponse;
import io.munzil.munzil.domain.auth.service.CheckAccountIdExistService;
import io.munzil.munzil.domain.auth.service.CheckNicknameExistsService;
import io.munzil.munzil.domain.auth.service.UserSignInService;
import io.munzil.munzil.domain.auth.service.UserTokenRefreshService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserSignInService userSignInService;
    private final CheckAccountIdExistService checkAccountIdExistService;
    private final CheckNicknameExistsService checkNicknameExistsService;
    private final UserTokenRefreshService userTokenRefreshService;

    @Operation(summary = "로그인")
    @PostMapping("/token")
    public UserTokenResponse userSignIn(@RequestBody @Valid UserSignInRequest request) {
        return userSignInService.execute(request);
    }

    @Operation(summary = "토큰 재발급")
    @PatchMapping("/token")
    public UserTokenRefreshResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return userTokenRefreshService.execute(refreshToken);
    }

    @Operation(summary= "아이디 중복 체크")
    @RequestMapping(value = "/account-id", method = RequestMethod.HEAD)
    public void checkAccountIdExist(@NotBlank @RequestParam(name = "accountId") String accountId) {
        checkAccountIdExistService.execute(accountId);
    }

    @Operation(summary = "닉네임 중복 체크")
    @RequestMapping(value = "/nickname", method = RequestMethod.HEAD)
    public void checkNicknameExist(@NotBlank @RequestParam(name = "nickname") String nickname) {
        checkNicknameExistsService.execute(nickname);
    }
}
