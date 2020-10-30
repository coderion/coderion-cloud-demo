package pl.coderion.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderion.auth.UserAuthenticationService;
import pl.coderion.exception.AuthenticationException;
import pl.coderion.exception.BaseException;
import pl.coderion.exception.ErrorCodeEnum;
import pl.coderion.request.LoginRequest;
import pl.coderion.response.LoginResponse;

import javax.validation.Valid;

/**
 * Copyright (C) Coderion sp. z o.o.
 */
@Slf4j
@RestController
@RequestMapping("/api/public/user")
public class PublicUserController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    /**
     * Log in to the system
     * @param loginRequest
     * @return
     * @throws BaseException
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws BaseException {
        if (StringUtils.isBlank(loginRequest.getEmail())) {
            throw new IllegalArgumentException("No user e-mail address");
        }

        if (StringUtils.isBlank(loginRequest.getPassword())) {
            throw new IllegalArgumentException("No user password");
        }

        return userAuthenticationService
                .login(loginRequest.getEmail(), loginRequest.getPassword())
                .map(loginResponse -> ResponseEntity.ok(loginResponse))
                .orElseThrow(() -> new AuthenticationException(ErrorCodeEnum.USER_INVALID_USER_OR_PASSWORD, loginRequest.getEmail()));
    }
}
