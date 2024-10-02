package ru.forinnyy.tm.api.model;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

import javax.naming.AuthenticationException;

public interface ICommand {

    String getArgument();

    @NonNull
    String getDescription();

    @NonNull
    String getName();

    Role[] getRoles();

    void execute() throws AbstractFieldException, AbstractUserException, AbstractEntityException, AuthenticationException;

}
