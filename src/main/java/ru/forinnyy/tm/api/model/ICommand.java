package ru.forinnyy.tm.api.model;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;

public interface ICommand {

    String getArgument();

    String getDescription();

    String getName();

    void execute() throws AbstractEntityException, AbstractFieldException;

}
