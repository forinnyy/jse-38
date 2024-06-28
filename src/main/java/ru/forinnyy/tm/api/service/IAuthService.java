package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

public interface IAuthService {

    @NotNull
    User registry(@NotNull String login, @NotNull String password, @NotNull String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    void login(@Nullable String login, @Nullable String password) throws AbstractFieldException, AbstractUserException, AbstractEntityException;

    void logout();

    boolean isAuth();

    @NotNull
    String getUserId() throws AbstractUserException;

    @NotNull
    User getUser() throws AbstractUserException, AbstractFieldException;

    void checkRoles(@Nullable Role[] roles) throws AbstractUserException, AbstractFieldException;

}
