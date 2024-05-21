package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.model.User;

public interface IUserRepository extends IRepository<User> {

    User create(String login, String password);

    User create(String login, String password, String email);

    User create(String login, String password, Role role);

    User findByLogin(String login) throws LoginEmptyException;

    User findByEmail(String email) throws ExistsEmailException;

    Boolean isLoginExist(String login);

    Boolean isEmailExist(String email);

}
