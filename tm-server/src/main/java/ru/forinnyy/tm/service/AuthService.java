package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.field.PasswordEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

import javax.naming.AuthenticationException;

public final class AuthService implements IAuthService {

    @NonNull
    private final IUserService userService;

    @NonNull
    private final IPropertyService propertyService;

    public AuthService(
            @NonNull IPropertyService propertyService,
            @NonNull IUserService userService
    ) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @NonNull
    @Override
    public User registry(@NonNull final String login, @NonNull final String password, @NonNull final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        return userService.create(login, password, email);
    }

    @Override
    @SneakyThrows
    public User check(String login, String password) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = userService.findByLogin(login);
        if (user == null) throw new PermissionException();
        final boolean locked = user.isLocked() == null || user.isLocked();
        if (locked) throw new PermissionException();
        final String hash = HashUtil.salt(propertyService, password);
        if (hash == null) throw new AuthenticationException();
        if (!hash.equals(user.getPasswordHash())) throw new PermissionException();
        return user;
    }

}
