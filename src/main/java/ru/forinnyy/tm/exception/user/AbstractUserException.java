package ru.forinnyy.tm.exception.user;

import ru.forinnyy.tm.exception.AbstractException;

public abstract class AbstractUserException extends AbstractException {

    public AbstractUserException() {
    }

    public AbstractUserException(String message) {
        super(message);
    }

    public AbstractUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractUserException(Throwable cause) {
        super(cause);
    }

    public AbstractUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
