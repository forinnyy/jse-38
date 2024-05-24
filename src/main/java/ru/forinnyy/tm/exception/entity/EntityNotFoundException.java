package ru.forinnyy.tm.exception.entity;

public final class EntityNotFoundException extends AbstractEntityException {

    public EntityNotFoundException() {
        super("Error! Entity not found...");
    }
}
