package ru.forinnyy.tm.api.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

import javax.naming.AuthenticationException;

public interface ICommand {

    @Nullable
    String getArgument();

    @NotNull
    String getDescription();

    @NotNull
    String getName();

    @Nullable
    Role[] getRoles();

    void execute() throws AbstractFieldException, AbstractUserException, AbstractEntityException, AuthenticationException;

}
