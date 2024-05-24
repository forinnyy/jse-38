package ru.forinnyy.tm.exception.user;

public final class PermissionException extends AbstractMethodError {

    public PermissionException() {
        super("Error! Permission is incorrect...");
    }

}
