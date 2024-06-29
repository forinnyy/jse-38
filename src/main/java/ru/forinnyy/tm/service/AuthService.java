package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.AccessDeniedException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

import java.util.Arrays;

public final class AuthService implements IAuthService {

    @NotNull
    private final IUserService userService;

    @Nullable
    private String userId;

    public AuthService(@NotNull IUserService userService) {
        this.userService = userService;
    }

    @NotNull
    @Override
    public User registry(@NotNull final String login, @NotNull final String password, @NotNull final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        return userService.create(login, password, email);
    }

    @Override
    public void login(@Nullable final String login, @Nullable final String password) throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final User user = userService.findByLogin(login);
        if (user == null) throw new PermissionException();
        final boolean locked = user.isLocked() == null || user.isLocked();
        if (locked) throw new AccessDeniedException();
        @NotNull final String hash = HashUtil.salt(password);
        if (!hash.equals(user.getPasswordHash())) throw new PermissionException();
        userId = user.getId();
    }

    @Override
    public void logout() {
        userId = null;
    }

    @Override
    public boolean isAuth() {
        return userId != null;
    }

    @NotNull
    @Override
    public String getUserId() throws AbstractUserException {
        if (!isAuth()) throw new AccessDeniedException();
        return userId;
    }

    @NotNull
    @Override
    public User getUser() throws AbstractUserException, AbstractFieldException {
        if (!isAuth()) throw new AccessDeniedException();
        @Nullable final User user = userService.findOneById(userId);
        if (user == null) throw new AccessDeniedException();
        return user;
    }

    @Override
    public void checkRoles(@Nullable final Role[] roles) throws AbstractUserException, AbstractFieldException {
        if (roles == null) return;
        @NotNull final User user = getUser();
        @NotNull final Role role = user.getRole();
        final boolean hasRole = Arrays.asList(roles).contains(role);
        if (!hasRole) throw new PermissionException();
    }

}
