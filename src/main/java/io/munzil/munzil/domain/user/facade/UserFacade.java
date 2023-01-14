package io.munzil.munzil.domain.user.facade;

import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.domain.repository.UserRepository;
import io.munzil.munzil.domain.user.exception.AlreadyAccountIdExistsException;
import io.munzil.munzil.domain.user.exception.AlreadyNicknameExistException;
import io.munzil.munzil.domain.user.exception.AlreadyUserExistException;
import io.munzil.munzil.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(accountId);
    }

    public User getUserByAccountId(String accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String accountId) {
        if (userRepository.findByAccountId(accountId).isPresent()) {
            throw AlreadyUserExistException.EXCEPTION;
        }
    }

    public void checkNicknameExists(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw AlreadyNicknameExistException.EXCEPTION;
        }
    }

    public void checkAccountIdeExists(String accountId) {
        if (userRepository.findByAccountId(accountId).isPresent()) {
            throw AlreadyAccountIdExistsException.EXCEPTION;
        }
    }
}
