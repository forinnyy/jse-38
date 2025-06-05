package ru.forinnyy.tm.exception.entity;

public final class TaskNotFoundException extends AbstractEntityException {

    public TaskNotFoundException() {
        super("Error! Task not found...");
    }

}
