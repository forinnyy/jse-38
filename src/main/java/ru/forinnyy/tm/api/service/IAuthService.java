package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

import javax.naming.AuthenticationException;

public interface IAuthService {

    @NonNull
    User registry(@NonNull String login, @NonNull String password, @NonNull String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    void login(String login, String password) throws AbstractFieldException, AbstractUserException, AbstractEntityException, AuthenticationException;

    void logout();

    boolean isAuth();

    @NonNull
    String getUserId() throws AbstractUserException;

    @NonNull
    User getUser() throws AbstractUserException, AbstractFieldException;

    void checkRoles(Role[] roles) throws AbstractUserException, AbstractFieldException;

}
