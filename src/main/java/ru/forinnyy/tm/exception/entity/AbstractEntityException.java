package ru.forinnyy.tm.exception.entity;

import ru.forinnyy.tm.exception.AbstractException;

public abstract class AbstractEntityException extends AbstractException {

    public AbstractEntityException() {
    }

    public AbstractEntityException(String message) {
        super(message);
    }

    public AbstractEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractEntityException(Throwable cause) {
        super(cause);
    }

    public AbstractEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
