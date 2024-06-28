package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

public interface IUserService extends IService<User> {

    @NotNull
    User create(@Nullable String login, @Nullable String password) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    @NotNull
    User create(@Nullable String login, @Nullable String password, @Nullable String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    @NotNull
    User create(@Nullable String login, @Nullable String password, @Nullable Role role) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    @Nullable
    User findByLogin(@Nullable String login) throws AbstractFieldException, AbstractEntityException;

    @Nullable
    User findByEmail(@Nullable String email) throws AbstractUserException, AbstractEntityException;

    @Nullable
    User removeByLogin(@Nullable String login) throws AbstractEntityException, AbstractFieldException;

    @Nullable
    User removeByEmail(@Nullable String email) throws AbstractEntityException, AbstractUserException, AbstractFieldException;

    @NotNull
    User setPassword(@Nullable String id, @Nullable String password) throws AbstractFieldException, AbstractEntityException;

    @NotNull
    User updateUser(@Nullable String id, @Nullable String firstName, @Nullable String lastName, @Nullable String middleName) throws AbstractFieldException, AbstractEntityException;

    @NotNull
    Boolean isLoginExist(@Nullable String login);

    @NotNull
    Boolean isEmailExist(@Nullable String email);

    void lockUserByLogin(@Nullable String login) throws AbstractFieldException, AbstractEntityException;

    void unlockUserByLogin(@Nullable String login) throws AbstractFieldException, AbstractEntityException;

}
