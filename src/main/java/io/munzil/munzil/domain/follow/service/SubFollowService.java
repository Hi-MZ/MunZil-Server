package io.munzil.munzil.domain.follow.service;

import io.munzil.munzil.domain.follow.domain.Follow;
import io.munzil.munzil.domain.follow.domain.repository.FollowRepository;
import io.munzil.munzil.domain.follow.exception.FollowNotFoundException;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubFollowService {
    private final UserFacade userFacade;
    private final FollowRepository followRepository;

    @Transactional
    public void execute(Long userIdToDelete) {
        User queryUser = userFacade.getCurrentUser();
        User targetUser = userFacade.getUserById(userIdToDelete);

        Follow relation = followRepository.findByUserAndTargetUser(queryUser, targetUser)
                .orElseThrow(() -> FollowNotFoundException.EXCEPTION);

        followRepository.delete(relation);
    }
}