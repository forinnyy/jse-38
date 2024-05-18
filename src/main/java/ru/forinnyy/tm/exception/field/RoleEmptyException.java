package ru.forinnyy.tm.exception.field;


public final class RoleEmptyException extends AbstractFieldException {

    public RoleEmptyException() {
        super("Error! Role is empty...");
    }

}
