package ru.forinnyy.tm.api.repository;

import lombok.NonNull;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.model.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(@NonNull String login) throws LoginEmptyException;

    User findByEmail(@NonNull String email) throws ExistsEmailException;

    @NonNull
    Boolean isLoginExist(@NonNull String login);

    @NonNull
    Boolean isEmailExist(@NonNull String email);

}
