package io.munzil.munzil.domain.user.service;

import io.munzil.munzil.domain.auth.exception.PasswordMisMatchException;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.facade.UserFacade;
import io.munzil.munzil.domain.user.presentation.dto.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdatePasswordService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UpdatePasswordRequest request) {
        User user = userFacade.getCurrentUser();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }
}
