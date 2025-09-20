package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

import java.util.List;

public interface IUserService extends IService<User> {

    @SneakyThrows
    void initTable();

    @NonNull
    User create(String login, String password) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    @NonNull
    User create(String login, String password, String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    @NonNull
    User create(String login, String password, Role role) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    @NonNull
    User findByLogin(String login) throws AbstractFieldException, AbstractEntityException;

    @NonNull
    User findByEmail(String email) throws AbstractUserException, AbstractEntityException;

    @NonNull
    User removeByLogin(String login) throws AbstractEntityException, AbstractFieldException;

    @NonNull
    User removeByEmail(String email) throws AbstractEntityException, AbstractUserException, AbstractFieldException;

    @NonNull
    User setPassword(@NonNull String id, @NonNull String password) throws AbstractFieldException, AbstractEntityException;

    @NonNull
    User updateUser(String id, String firstName, String lastName, String middleName) throws AbstractFieldException, AbstractEntityException;

    @NonNull
    Boolean isLoginExist(String login);

    @NonNull
    Boolean isEmailExist(String email);

    void lockUserByLogin(String login) throws AbstractFieldException, AbstractEntityException;

    void unlockUserByLogin(String login) throws AbstractFieldException, AbstractEntityException;

    @NonNull
    List<String> listProfiles();

}
