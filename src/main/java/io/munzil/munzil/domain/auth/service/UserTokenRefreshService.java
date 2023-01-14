package io.munzil.munzil.domain.auth.service;

import io.munzil.munzil.domain.auth.domain.RefreshToken;
import io.munzil.munzil.domain.auth.domain.repository.RefreshTokenRepository;
import io.munzil.munzil.domain.auth.exception.RefreshTokenNotFoundException;
import io.munzil.munzil.domain.auth.presentation.dto.response.UserTokenRefreshResponse;
import io.munzil.munzil.global.property.jwt.JwtProperties;
import io.munzil.munzil.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserTokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public UserTokenRefreshResponse execute(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(jwtTokenProvider.parseToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(redisRefreshToken.getAccountId());
        redisRefreshToken.updateToken(newRefreshToken, jwtProperties.getRefreshExp());

        String accessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getAccountId());
        return UserTokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }
}
