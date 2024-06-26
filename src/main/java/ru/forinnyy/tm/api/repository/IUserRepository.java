package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.model.User;

public interface IUserRepository extends IRepository<User> {

    @NotNull
    User create(String login, String password);

    @NotNull
    User create(String login, String password, String email);

    @NotNull
    User create(String login, String password, Role role);

    @Nullable
    User findByLogin(String login) throws LoginEmptyException;

    @Nullable
    User findByEmail(String email) throws ExistsEmailException;

    @NotNull
    Boolean isLoginExist(String login);

    @NotNull
    Boolean isEmailExist(String email);

}
