package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

public interface IAuthService {

    @NotNull
    User registry(String login, String password, String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    void login(String login, String password) throws AbstractFieldException, AbstractUserException, AbstractEntityException;

    void logout();

    boolean isAuth();

    @NotNull
    String getUserId() throws AbstractUserException;

    @NotNull
    User getUser() throws AbstractUserException, AbstractFieldException;

    void checkRoles(Role[] roles) throws AbstractUserException, AbstractFieldException;

}
