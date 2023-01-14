package io.munzil.munzil.domain.user.presentation;

import io.munzil.munzil.domain.auth.presentation.dto.response.UserTokenResponse;
import io.munzil.munzil.domain.user.presentation.dto.request.UpdatePasswordRequest;
import io.munzil.munzil.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import io.munzil.munzil.domain.user.presentation.dto.request.UserSignUpRequest;
import io.munzil.munzil.domain.user.presentation.dto.response.QueryMyFeedPagesResponse;
import io.munzil.munzil.domain.user.presentation.dto.response.QueryMyProfileResponse;
import io.munzil.munzil.domain.user.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final QueryMyProfileService queryMyProfileService;
    private final QueryMyFeedPagesService queryMyFeedPagesService;
    private final QueryMyBookmarksPagesService queryMyBookmarksPagesService;
    private final UserSignUpService userSignUpService;
    private final UpdateUserInfoService updateUserInfoService;
    private final UpdatePasswordService updatePasswordService;
    private final UserLogoutService userLogoutService;
    private final UserWithdrawalService userWithdrawalService;


    @Operation(summary = "MY 프로필 정보 조회")
    @GetMapping
    public QueryMyProfileResponse queryMyProfile() {
        return queryMyProfileService.execute();
    }

    @Operation(summary = "MY 게시글 조회")
    @GetMapping("/feeds")
    public QueryMyFeedPagesResponse queryMyFeeds(@RequestParam(defaultValue = "0") Long cursorId) {
        return queryMyFeedPagesService.execute(cursorId);
    }

    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
    }

    @Operation(summary = "내 정보 수정")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateUserInfo(@RequestPart(required = false) MultipartFile image, @RequestPart @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.execute(image, request);
    }

    @Operation(summary = "비밀번호 변경")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @Operation(summary = "로그아웃")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout() {
        userLogoutService.execute();
    }

    @Operation(summary = "회원탈퇴")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave")
    public void leave() {
        userWithdrawalService.execute();
    }
}
