package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.model.User;

import java.util.List;

public interface IUserRepository {

    User add(User user) throws AbstractEntityException;

    List<User> findAll();

    User findById(String id) throws AbstractFieldException;

    User findByLogin(String login) throws LoginEmptyException;

    User findByEmail(String email) throws ExistsEmailException;

    User remove(User user) throws UserNotFoundException;

    Boolean isLoginExist(String login);

    Boolean isEmailExist(String email);

}
