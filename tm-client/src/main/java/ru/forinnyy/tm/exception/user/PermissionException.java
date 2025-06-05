package ru.forinnyy.tm.exception.user;

public final class PermissionException extends AbstractUserException {

    public PermissionException() {
        super("Error! Permission is incorrect...");
    }

}
