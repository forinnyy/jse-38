package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

public interface IUserService extends IUserRepository {

    User create(String login, String password) throws AbstractUserException, AbstractFieldException;

    User create(String login, String password, String email)throws AbstractUserException, AbstractFieldException;

    User create(String login, String password, Role role) throws AbstractUserException, AbstractFieldException;

    User removeById(String id) throws AbstractFieldException, AbstractEntityException;

    User removeByLogin(String login) throws AbstractEntityException, AbstractFieldException;

    User removeByEmail(String email) throws AbstractEntityException, AbstractUserException, AbstractFieldException;

    User setPassword(String id, String password) throws AbstractFieldException, AbstractEntityException;

    User updateUser(String id, String firstName, String lastName, String middleName) throws AbstractFieldException, AbstractEntityException;

}
