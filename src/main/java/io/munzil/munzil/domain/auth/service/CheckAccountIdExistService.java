package io.munzil.munzil.domain.auth.service;

import io.munzil.munzil.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckAccountIdExistService {

    private final UserFacade userFacade;

    public void execute(String accountId) {
        userFacade.checkAccountIdeExists(accountId);
    }
}
