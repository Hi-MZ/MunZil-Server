package io.munzil.munzil.domain.follow.service;

import io.munzil.munzil.domain.follow.domain.Follow;
import io.munzil.munzil.domain.follow.domain.repository.FollowRepository;
import io.munzil.munzil.domain.follow.presentation.dto.response.FollowInfo;
import io.munzil.munzil.domain.follow.presentation.dto.response.GetAllFollowingResponse;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryFollowService {
    private final UserFacade userFacade;
    private final FollowRepository followRepository;

    @Transactional(readOnly = true)
    public GetAllFollowingResponse getAllFollowing(Long userId) {
        User user = userFacade.getUserById(userId);

        List<Follow> followings = followRepository.findAllByUserWithTarget(user);
        List<FollowInfo> followingList = followings.stream().map(follow -> {
            User targetUser = follow.getTargetUser();
            boolean isFollowingFollowsMe = followRepository
                    .findByUserAndTargetUser(targetUser, user)
                    .isPresent();

            return FollowInfo.builder()
                    .userId(targetUser.getId())
                    .nickname(targetUser.getNickname())
                    .profileImageUrl(targetUser.getProfileImageUrl())
                    .isFollow(isFollowingFollowsMe)
                    .build();
        }).collect(Collectors.toList());

        return new GetAllFollowingResponse(followingList);
    }

}