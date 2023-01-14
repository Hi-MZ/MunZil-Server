package io.munzil.munzil.domain.follow.service;

import io.munzil.munzil.domain.follow.domain.Follow;
import io.munzil.munzil.domain.follow.domain.repository.FollowRepository;
import io.munzil.munzil.domain.follow.presentation.dto.response.FollowInfo;
import io.munzil.munzil.domain.follow.presentation.dto.response.GetAllFollowerResponse;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryFollowerService {

    private final UserFacade userFacade;
    private final FollowRepository followRepository;

    @Transactional(readOnly = true)
    public GetAllFollowerResponse execute(Long userId) {
        User user = userFacade.getUserById(userId);

        List<Follow> followerList = followRepository.findAllByTargetUserWithUser(user);
        List<FollowInfo> followerInfoList = followerList.stream()
                .map(follow -> {
                    User follower = follow.getUser();
                    boolean isUserFollowingFollower = followRepository
                            .findByUserAndTargetUser(user, follower)
                            .isPresent();

                    return FollowInfo.builder()
                            .userId(follower.getId())
                            .nickname(follower.getNickname())
                            .profileImageUrl(follower.getProfileImageUrl())
                            .isFollow(isUserFollowingFollower)
                            .build();
                })
                .collect(Collectors.toList());

        return new GetAllFollowerResponse(followerInfoList);
    }
}