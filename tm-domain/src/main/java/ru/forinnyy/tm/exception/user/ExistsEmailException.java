package ru.forinnyy.tm.exception.user;

public final class ExistsEmailException extends AbstractUserException {

    public ExistsEmailException() {
        super("Error! Email is empty...");
    }

}
