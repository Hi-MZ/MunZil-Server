package io.munzil.munzil.domain.follow.presentation;


import io.munzil.munzil.domain.follow.presentation.dto.response.GetAllFollowerResponse;
import io.munzil.munzil.domain.follow.presentation.dto.response.GetAllFollowingResponse;
import io.munzil.munzil.domain.follow.service.AddFollowService;
import io.munzil.munzil.domain.follow.service.QueryFollowService;
import io.munzil.munzil.domain.follow.service.QueryFollowerService;
import io.munzil.munzil.domain.follow.service.SubFollowService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/follows")
@RestController
public class FollowController {
    private final QueryFollowService queryFollowService;

    private final QueryFollowerService queryFollowerService;
    private final AddFollowService addFollowService;

    private final SubFollowService subFollowService;

    @Operation(summary = "팔로우 추가")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{user-id}")
    public void addFollow(@PathVariable("user-id") Long userId) {
        addFollowService.addFollow(userId);
    }

    @Operation(summary = "팔로잉 보기")
    @GetMapping("/following/{user-id}")
    public GetAllFollowingResponse getAllFollowing(@PathVariable("user-id") Long userId) {
        return queryFollowService.getAllFollowing(userId);
    }

    @Operation(summary = "팔로워 보기")
    @GetMapping("/follower/{user-id}")
    public GetAllFollowerResponse getAllFollower(@PathVariable("user-id") Long userId) {
        return queryFollowerService.execute(userId);
    }

    @Operation(summary = "팔로우 삭제")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{user-id}/following")
    public void deleteFollowing(@PathVariable("user-id") Long userId) {
        subFollowService.execute(userId);
    }
}