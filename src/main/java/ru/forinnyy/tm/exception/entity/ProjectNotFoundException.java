package ru.forinnyy.tm.exception.entity;

public final class ProjectNotFoundException extends AbstractEntityException {

    public ProjectNotFoundException() {
        super("Error! Project not found...");
    }

}
