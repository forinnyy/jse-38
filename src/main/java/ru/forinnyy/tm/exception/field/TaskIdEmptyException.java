package ru.forinnyy.tm.exception.field;

public final class TaskIdEmptyException extends AbstractFieldException {

    public TaskIdEmptyException() {
        super("Error! Task is is empty...");
    }

}
