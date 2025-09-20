package ru.forinnyy.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.api.service.ISessionService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.field.PasswordEmptyException;
import ru.forinnyy.tm.exception.user.AccessDeniedException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.Session;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.CryptUtil;
import ru.forinnyy.tm.util.HashUtil;

import javax.naming.AuthenticationException;
import java.util.Date;

public final class AuthService implements IAuthService {

    @NonNull
    private final IUserService userService;

    @NonNull
    private final IPropertyService propertyService;

    @NonNull
    private final ISessionService sessionService;

    public AuthService(
            @NonNull final IPropertyService propertyService,
            @NonNull final IUserService userService,
            @NonNull final ISessionService sessionService
    ) {
        this.propertyService = propertyService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @NonNull
    @Override
    @SneakyThrows
    public String login(final String login, final String password) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();

        final User user = userService.findByLogin(login);
        if (user == null) throw new AccessDeniedException();
        if (user.isLocked()) throw new AccessDeniedException();

        final String hash = HashUtil.salt(propertyService, password);
        if (!hash.equals(user.getPasswordHash())) throw new PermissionException();

        return getToken(user);
    }

    @NonNull
    @SneakyThrows
    private String getToken(@NonNull final User user) {
        return getToken(createSession(user));
    }

    @NonNull
    @SneakyThrows
    private String getToken(@NonNull final Session session) {
        @NonNull final ObjectMapper objectMapper = new ObjectMapper();
        @NonNull final String token = objectMapper.writeValueAsString(session);
        @NonNull final String sessionKey = propertyService.getSessionKey();
        return CryptUtil.encrypt(sessionKey, token);
    }

    @NonNull
    @SneakyThrows
    private Session createSession(@NonNull final User user) {
        @NonNull final Session session = new Session();
        session.setUserId(user.getId());
        session.setRole(user.getRole() != null ? user.getRole() : Role.USUAL);
        session.setDate(new Date());
        return sessionService.add(session);
    }

    @NonNull
    @Override
    @SneakyThrows
    public Session validateToken(final String token) {
        if (token == null) throw new AccessDeniedException();

        @NonNull final String sessionKey = propertyService.getSessionKey();
        @NonNull final String json;
        try {
            json = CryptUtil.decrypt(sessionKey, token);
        } catch (@NonNull final Exception e) {
            throw new AccessDeniedException();
        }

        @NonNull final ObjectMapper objectMapper = new ObjectMapper();
        @NonNull final Session session = objectMapper.readValue(json, Session.class);

        @NonNull final Date currentDate = new Date();
        @NonNull final Date sessionDate = session.getDate();
        final long deltaSeconds = (currentDate.getTime() - sessionDate.getTime()) / 1000;

        final int timeout = propertyService.getSessionTimeout();
        if (deltaSeconds > timeout) throw new AccessDeniedException();

        if (!sessionService.existsById(session.getId())) throw new AccessDeniedException();

        return session;
    }

    @Override
    @SneakyThrows
    public void invalidate(final Session session) {
        if (session == null) return;
        sessionService.remove(session);
    }

    @NonNull
    @Override
    @SneakyThrows
    public User registry(@NonNull final String login, @NonNull final String password, @NonNull final String email) {
        return userService.create(login, password, email);
    }

    @Override
    @SneakyThrows
    public User check(final String login, final String password) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();

        final User user = userService.findByLogin(login);
        if (user == null) throw new PermissionException();

        if (user.isLocked()) throw new PermissionException();

        final String hash = HashUtil.salt(propertyService, password);
        if (hash == null) throw new AuthenticationException();
        if (!hash.equals(user.getPasswordHash())) throw new PermissionException();

        return user;
    }

}
