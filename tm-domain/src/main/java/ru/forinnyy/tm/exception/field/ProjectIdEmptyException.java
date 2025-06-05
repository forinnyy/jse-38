package ru.forinnyy.tm.exception.field;

public final class ProjectIdEmptyException extends AbstractFieldException {

    public ProjectIdEmptyException() {
        super("Error! Project id is empty...");
    }

}
