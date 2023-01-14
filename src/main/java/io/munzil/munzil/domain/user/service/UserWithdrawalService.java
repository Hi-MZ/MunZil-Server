package io.munzil.munzil.domain.user.service;

import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.domain.repository.UserRepository;
import io.munzil.munzil.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserWithdrawalService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        userRepository.delete(user);
    }
}
