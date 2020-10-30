package pl.coderion.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Copyright (C) Coderion sp. z o.o.
 */
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
