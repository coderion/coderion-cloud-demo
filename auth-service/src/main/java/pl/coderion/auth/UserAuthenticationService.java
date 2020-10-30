package pl.coderion.auth;

import pl.coderion.exception.BaseException;
import pl.coderion.response.LoginResponse;
import pl.coderion.user.User;

import java.util.Optional;

/**
 * https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/
 */
public interface UserAuthenticationService {

    /**
     * Logs in with the given {@code username} and {@code password}
     * @param username
     * @param password
     * @return
     */
    Optional<LoginResponse> login(String username, String password) throws BaseException;

    /**
     * Finds a user by its token
     * @param token
     * @return
     */
    Optional<User> findByToken(String token);
}
