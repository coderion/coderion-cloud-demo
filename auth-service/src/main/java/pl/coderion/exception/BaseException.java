package pl.coderion.exception;

import lombok.Getter;

public abstract class BaseException extends Exception {

    @Getter private ErrorCodeEnum errorCode;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}