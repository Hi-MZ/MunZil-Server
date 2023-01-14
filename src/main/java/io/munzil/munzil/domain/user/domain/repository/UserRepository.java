package io.munzil.munzil.domain.user.domain.repository;

import io.munzil.munzil.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAccountId(String accountId);
    Optional<User> findByNickname(String nickname);

}
