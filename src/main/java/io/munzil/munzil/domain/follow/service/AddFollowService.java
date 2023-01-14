package io.munzil.munzil.domain.follow.service;

import io.munzil.munzil.domain.follow.domain.Follow;
import io.munzil.munzil.domain.follow.domain.FollowId;
import io.munzil.munzil.domain.follow.domain.repository.FollowRepository;
import io.munzil.munzil.domain.follow.exception.FollowAlreadyExistsException;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.domain.repository.UserRepository;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddFollowService {
    private final UserFacade userFacade;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addFollow(Long userIdToFollow) {
        User currentUser = userFacade.getCurrentUser();
        User targetUser = userRepository.findById(userIdToFollow)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        boolean isAlreadyExists = followRepository
                .findByUserAndTargetUser(currentUser, targetUser)
                .isPresent();

        if (isAlreadyExists) {
            throw FollowAlreadyExistsException.EXCEPTION;
        }

        FollowId followId = FollowId.builder()
                .user(currentUser.getId())
                .targetUser(targetUser.getId())
                .build();
        Follow follow = Follow.builder()
                .id(followId)
                .build();

        followRepository.save(follow);
    }
}