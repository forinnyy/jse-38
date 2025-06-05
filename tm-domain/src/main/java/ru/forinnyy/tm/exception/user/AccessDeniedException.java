package ru.forinnyy.tm.exception.user;

public final class AccessDeniedException extends AbstractUserException {

    public AccessDeniedException() {
        super("Error! You are not logged in. Please log in and try again...");
    }

}
