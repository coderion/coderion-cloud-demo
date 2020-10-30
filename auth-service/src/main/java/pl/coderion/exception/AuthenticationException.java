package pl.coderion.exception;

import lombok.Getter;

public class AuthenticationException extends BaseException {

    @Getter
    private String email;

    public AuthenticationException() {
    }

    public AuthenticationException(ErrorCodeEnum errorCodeEnum, String email) {
        super(errorCodeEnum);
        this.email = email;
    }
}
