package ru.forinnyy.tm.exception.field;

import ru.forinnyy.tm.exception.AbstractException;

public abstract class AbstractFieldException extends AbstractException {

    public AbstractFieldException() {
    }

    public AbstractFieldException(String message) {
        super(message);
    }

    public AbstractFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractFieldException(Throwable cause) {
        super(cause);
    }

    public AbstractFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
