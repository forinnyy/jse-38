package ru.forinnyy.tm.exception.entity;

public final class UserNotFoundException extends AbstractEntityException {

    public UserNotFoundException() {
        super("Error! User not found...");
    }

}
