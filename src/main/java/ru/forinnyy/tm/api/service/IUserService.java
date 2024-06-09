package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

public interface IUserService extends IService<User> {

    User create(String login, String password) throws AbstractUserException, AbstractFieldException;

    User create(String login, String password, String email)throws AbstractUserException, AbstractFieldException;

    User create(String login, String password, Role role) throws AbstractUserException, AbstractFieldException;

    User findByLogin(String login) throws AbstractFieldException;

    User findByEmail(String email) throws AbstractUserException;

    User removeByLogin(String login) throws AbstractEntityException, AbstractFieldException;

    User removeByEmail(String email) throws AbstractEntityException, AbstractUserException, AbstractFieldException;

    User setPassword(String id, String password) throws AbstractFieldException, AbstractEntityException;

    User updateUser(String id, String firstName, String lastName, String middleName) throws AbstractFieldException, AbstractEntityException;

    Boolean isLoginExist(String login);

    Boolean isEmailExist(String email);

    void lockUserByLogin(String login) throws AbstractFieldException, AbstractEntityException;

    void unlockUserByLogin(String login) throws AbstractFieldException, AbstractEntityException;

}
