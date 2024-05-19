package ru.forinnyy.tm.api.model;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public interface ICommand {

    String getArgument();

    String getDescription();

    String getName();

    void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException;

}
