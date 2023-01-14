package io.munzil.munzil.domain.auth.service;

import io.munzil.munzil.domain.auth.exception.PasswordMisMatchException;
import io.munzil.munzil.domain.auth.presentation.dto.request.UserSignInRequest;
import io.munzil.munzil.domain.auth.presentation.dto.response.UserTokenResponse;
import io.munzil.munzil.domain.user.domain.User;
import io.munzil.munzil.domain.user.domain.repository.UserRepository;
import io.munzil.munzil.global.exception.UserNotFoundException;
import io.munzil.munzil.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSignInService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserTokenResponse execute(UserSignInRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .refreshToken(refreshToken)
                .build();
    }
}
