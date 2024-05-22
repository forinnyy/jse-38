package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.AccessDeniedException;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

public final class AuthService implements IAuthService {

    private final IUserService userService;

    private String userId;

    public AuthService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User registry(final String login, final String password, final String email) throws AbstractUserException, AbstractFieldException {
        return userService.create(login, password, email);
    }

    @Override
    public void login(final String login, final String password) throws AbstractFieldException, AbstractUserException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = userService.findByLogin(login);
        if (user == null) throw new AccessDeniedException();
        final String hash = HashUtil.salt(password);
        if (!hash.equals(user.getPasswordHash())) throw new AccessDeniedException();
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

    @Override
    public String getUserId() throws AbstractUserException {
        if (!isAuth()) throw new AccessDeniedException();
        return userId;
    }

    @Override
    public User getUser() throws AbstractUserException, AbstractFieldException {
        if (!isAuth()) throw new AccessDeniedException();
        final User user = userService.findOneById(userId);
        if (user == null) throw new AccessDeniedException();
        return user;
    }

}
