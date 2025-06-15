package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Session;
import ru.forinnyy.tm.model.User;

public interface IAuthService {

    @NonNull
    String login(String login, String password);

    @NonNull
    Session validateToken(String token);

    void invalidate(Session session);

    @NonNull
    User registry(@NonNull String login, @NonNull String password, @NonNull String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException;

    User check(String login, String password);

}
